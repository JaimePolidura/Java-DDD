package es.jaime.javaddd.application.concurrency;

import java.util.concurrent.*;

public final class ManualFuture<T> implements Future<T> {
    private final CountDownLatch latch;
    private Exception exception;
    private State state;
    private T result;

    public ManualFuture (T value){
        this.latch = new CountDownLatch(1);
        this.state = State.PENDING;
        this.result = value;
    }

    public static <T> ManualFuture<T> create() {
        return new ManualFuture<>(null);
    }

    public static <T> ManualFuture<T> createWithDefault(T defaultValue) {
        return new ManualFuture<>(defaultValue);
    }

    public void complete(T finalResult) {
        this.result = finalResult;
        this.state = State.DONE_SUCCESS;
    }

    public void complete() {
        this.state = State.DONE_SUCCESS;
    }

    public void error() {
        this.state = State.DONE_FAILURE;
    }

    public void error(Exception e) {
        this.state = State.DONE_FAILURE;
        this.exception = e;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.state = State.CANCELLED;

        return true;
    }

    @Override
    public boolean isCancelled() {
        return this.state == State.CANCELLED;
    }

    @Override
    public boolean isDone() {
        return this.state == State.DONE_FAILURE || this.state == State.DONE_SUCCESS;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        boolean hasTimeout = !latch.await(timeout, unit);

        if(hasTimeout){
            throw new TimeoutException();
        }

        return get();
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        latch.await();

        if(state == State.DONE_FAILURE) {
            throw new ExecutionException(this.exception);
        }
        if(state == State.DONE_SUCCESS){
            return this.result;
        }
        if(state == State.CANCELLED){
            throw new CancellationException();
        }

        throw new RuntimeException("Unexpected state");
    }

    private enum State {
        PENDING, DONE_SUCCESS, DONE_FAILURE, CANCELLED
    }
}
