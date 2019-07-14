/** 
 * Returns a copy of this RetryPolicy.
 */
public RetryPolicy<R> copy(){
  return new RetryPolicy<>(this);
}
