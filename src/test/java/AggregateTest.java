import es.jaime.javaddd.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public final class AggregateTest {
    @Test
    public void toPrimitives(){
        Aggregate testClass = new AggregateTestClass("name", 0);
        Map<String, Object> toPrimitives = testClass.toPrimitives();

        assertEquals(toPrimitives.get("name"), "name");
        assertEquals(toPrimitives.get("number"), 0.0);
    }


    @AllArgsConstructor
    private class AggregateTestClass extends Aggregate {
        @Getter private final String name;
        @Getter private final double number;
    }
}
