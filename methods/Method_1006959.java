/** 
 * Get a future for a non-null result from the callback. Only when the result is asked for (using  {@link Future#get()} or{@link Future#get(long,TimeUnit)} will the polling actually start.
 * @see Poller#poll(Callable)
 */
@Override public Future<S> poll(Callable<S> callable) throws Exception {
  return new DirectPollingFuture<>(interval,callable);
}
