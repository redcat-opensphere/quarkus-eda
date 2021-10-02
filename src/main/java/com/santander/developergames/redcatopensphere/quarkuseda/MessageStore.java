package com.santander.developergames.redcatopensphere.quarkuseda;

import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import com.santander.developergames.redcatopensphere.quarkuseda.model.SimpleMessage;

import org.apache.commons.collections4.queue.CircularFifoQueue;

@ApplicationScoped
public class MessageStore {

    private static final Logger LOGGER = Logger.getLogger(MessageStore.class);

    CircularFifoQueue<SimpleMessage> queue = new CircularFifoQueue<>(500);

    public void store(SimpleMessage message){

        queue.add(message);
        LOGGER.infof("Stored message %s - %s. Total in store is %s",message.getKey(), message.getValue(), queue.size());        
    }

    public List<SimpleMessage> listContents() {
        List<SimpleMessage> messageList = new ArrayList<>(queue.size());
        for (SimpleMessage message : queue){
            messageList.add(message);
        }
        return messageList;
    }

    public void clear(){
        queue.clear();
    }

}