package com.santander.developergames.redcatopensphere.quarkuseda;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.santander.developergames.redcatopensphere.quarkuseda.model.MyMessage;
import com.santander.developergames.redcatopensphere.quarkuseda.model.SimpleMessage;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@Path("api")
public class EdaResource {

    private static final Logger LOGGER = Logger.getLogger(EdaResource.class);

    @Inject
    @Channel("messages")
    Emitter<MyMessage> emitter;

  
    @Inject
    MessageStore store;

    @GET
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy Reactive. Use /topic endpoints with get or post to read or write messages";
    }

    @GET
    @Path("topic")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SimpleMessage> consume() {
        List<SimpleMessage> contents = store.listContents();
        LOGGER.infof("Reading %s messages from store",contents.size());
        return contents;
    }

    @POST
    @Path("topic")
    public Response publish(MyMessage message) {
        LOGGER.infof("Sending message %s to Kafka", message.getKey());
        emitter.send(message);
        return Response.accepted().build();
    }    

    @DELETE
    @Path("topic")
    public Response clear() {
        LOGGER.infof("Clearing messages");
        store.clear();
        return Response.ok().build();
    }    

}