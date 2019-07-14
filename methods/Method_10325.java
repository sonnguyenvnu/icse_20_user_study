/** 
 * Returns true if this task completed. Completion may be due to normal termination, an exception, or cancellation -- in all of these cases, this method will return true.
 * @return true if this task completed
 */
public boolean isFinished(){
  AsyncHttpRequest _request=request.get();
  return _request == null || _request.isDone();
}
