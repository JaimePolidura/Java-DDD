package es.jaime.javaddd.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Map;

public abstract class Aggregate implements Serializable {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Map<String, Object> toPrimitives(){
        return Aggregate.OBJECT_MAPPER.convertValue(this, Map.class);
    }
}
