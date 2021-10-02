package com.santander.developergames.redcatopensphere.quarkuseda;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

import com.santander.developergames.redcatopensphere.quarkuseda.model.MyMessage;
import com.santander.developergames.redcatopensphere.quarkuseda.model.SimpleMessage;

@ApplicationScoped
public class MessageConsumer {

    private static final Logger LOGGER = Logger.getLogger(MessageConsumer.class);

    @Inject
    MessageStore messageStore;

    @Incoming("messages-from-kafka")
    public void consume(MyMessage message) {
        
        LOGGER.infof("Consuming message %s - %s",message.getKey(), message.getValue());

        messageStore.store(new SimpleMessage(message.getKey(),message.getValue()));
    }

}