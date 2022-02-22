package es.jaime.javaddd.domain.database;

public interface TransactionManager {
    void start();

    void commit();

    void rollback();
}
