package com.santander.developergames.redcatopensphere.quarkuseda.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SimpleMessage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String key;
    private String value;

}