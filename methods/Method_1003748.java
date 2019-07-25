/** 
 * Causes  {@code this} yielding the promised value to be retried on error, after a fixed delay.
 * @param maxAttempts the maximum number of times to retry
 * @param delay the duration to wait between retry attempts
 * @param onError the error handler
 * @return a promise with a retry error handler
 * @see #retry(int,BiFunction)
 * @since 1.5
 * @deprecated since 1.7, use {@link #retry(RetryBuilder,BiAction)}
 */
@Deprecated default Promise<T> retry(int maxAttempts,Duration delay,@NonBlocking BiAction<? super Integer,? super Throwable> onError){
  Promise<Duration> delayPromise=Promise.value(delay);
  return retry(maxAttempts,(i,error) -> Operation.of(() -> onError.execute(i,error)).flatMap(delayPromise));
}
