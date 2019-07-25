/** 
 * Wraps the backoff policy in one that calls a method every time a new backoff is taken from the policy.
 */
public static BackoffPolicy wrap(BackoffPolicy delegate,Runnable onBackoff){
  return new WrappedBackoffPolicy(delegate,onBackoff);
}
