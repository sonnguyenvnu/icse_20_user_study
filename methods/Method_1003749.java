/** 
 * Causes  {@code this} yielding the promised value to be retried on error, after a calculated delay.
 * @param maxAttempts the maximum number of times to retry
 * @param onError the error handler
 * @return a promise with a retry error handler
 * @see #retry(int,Duration,BiAction)
 * @since 1.5
 * @deprecated since 1.7, use {@link #retry(RetryBuilder,BiAction)}
 */
@Deprecated default Promise<T> retry(int maxAttempts,BiFunction<? super Integer,? super Throwable,Promise<Duration>> onError){
  return transform(up -> down -> DefaultPromise.retryAttempt(1,maxAttempts,up,down,onError));
}
