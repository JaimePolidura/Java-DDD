package es.jaime.javaddd.domain.configuration;

import java.util.Map;
import java.util.Set;

public interface ConfigurationFileMapper {
    Map<String, Object> getConfigMap();
    Set<String> getUsingConfigsNames();
}
