package es.jaiem.javaddd.domain.cqrs.query;

public interface QueryBus {
    <R extends QueryResponse> R ask(Query query);
}
