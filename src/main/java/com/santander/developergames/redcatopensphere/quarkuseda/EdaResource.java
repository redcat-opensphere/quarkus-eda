package com.santander.developergames.redcatopensphere.quarkuseda;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.santander.developergames.redcatopensphere.quarkuseda.model.MyMessage;


import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import org.jboss.logging.Logger;

@Path("")
public class EdaResource {

    private static final Logger LOGGER = Logger.getLogger(EdaResource.class);

    @Channel("messages")
    Emitter<MyMessage> emitter;

    @Channel("messages-from-kafka")
    Multi<MyMessage> messages;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy Reactive";
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