package es.jaime.javaddd.domain.file;

import java.io.InputStream;

public interface FileResolver {
    InputStream resolve(String file);
}
