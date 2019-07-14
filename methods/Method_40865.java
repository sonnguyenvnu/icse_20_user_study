/** 
 * Records an execution  {@code result} as a success or failure based on the failure configuration as determined by{@link #isFailure(R,Throwable)}.
 * @see #isFailure(R,Throwable)
 */
public void recordResult(R result){
  recordResult(result,null);
}
