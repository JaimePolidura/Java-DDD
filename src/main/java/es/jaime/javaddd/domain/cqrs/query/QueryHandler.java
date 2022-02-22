package es.jaime.javaddd.domain.cqrs.query;

public interface QueryHandler<Q extends Query, R extends QueryResponse> {
    R handle(Q query);
}
