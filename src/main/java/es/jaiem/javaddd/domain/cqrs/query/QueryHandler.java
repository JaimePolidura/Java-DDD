package es.jaiem.javaddd.domain.cqrs.query;

public interface QueryHandler<Q extends Query, R extends QueryResponse> {
    R handle(Q query);
}
