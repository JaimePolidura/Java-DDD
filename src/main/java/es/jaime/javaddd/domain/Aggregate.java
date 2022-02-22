package es.jaime.javaddd.domain;

import java.io.Serializable;
import java.util.Map;

public abstract class Aggregate implements Serializable {
    public abstract Map<String, Object> toPrimitives();
}
