/** 
 * Records a failed execution and returns true if a retry can be performed for the  {@code failure}, else returns false and completes the execution. <p> Alias of  {@link #canRetryOn(Throwable)}
 * @throws NullPointerException if {@code failure} is null
 * @throws IllegalStateException if the execution is already complete
 */
public boolean recordFailure(Throwable failure){
  return canRetryOn(failure);
}
