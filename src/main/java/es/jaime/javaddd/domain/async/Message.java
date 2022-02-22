package es.jaime.javaddd.domain.async;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *{
 *   "type": "command",
 *   "name": "new-buy-order",
 *   "created_on": "12/12/12"
 *   "body": {
 *     "clientId": "jaime",
 *     "ticker": "AMZN",
 *     "executionPrice": 12
 *   },
 *   "meta": {}
 * }
 */
public interface Message extends Serializable {
    MessageType type();
    String name();

    Map<String, Object> body();

    default Map<String, Object> meta(){
        return new HashMap<>();
    }

    default UUID id(){
        return UUID.randomUUID();
    }

    default LocalDateTime date(){
        return LocalDateTime.now();
    }

    default Map<String, Object> toPrimitives() {
        return new HashMap<>() {{
            put("id", id().toString());
            put("created_on", date().toString());
            put("type", type().name());
            put("name", name());
            put("body", body());
            put("meta", meta());
        }};
    }
}
