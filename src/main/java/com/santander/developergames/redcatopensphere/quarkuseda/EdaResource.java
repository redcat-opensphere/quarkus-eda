package com.santander.developergames.redcatopensphere.quarkuseda;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.santander.developergames.redcatopensphere.quarkuseda.model.MyMessage;

import io.smallrye.mutiny.Multi;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
@Path("api")
public class EdaResource {

    private static final Logger LOGGER = Logger.getLogger(EdaResource.class);

    @Channel("messages")
    Emitter<MyMessage> emitter;

    @Channel("messages-from-kafka")
    Multi<MyMessage> messages;

    @GET
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy Reactive. Use /topic endpoints with get or post to read or write messages";
    }

    @GET
    @Path("topic")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<MyMessage> consume() {
        return messages;
    }



    @POST
    @Path("topic")
    public Response publish(MyMessage message) {
        LOGGER.infof("Sending message %s to Kafka", message.getKey());
        emitter.send(message);
        return Response.accepted().build();
    }    
}