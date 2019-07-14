/** 
 * Performs synchronous post-execution handling for a  {@code result}.
 */
protected ExecutionResult postExecute(ExecutionResult result){
  if (isFailure(result)) {
    result=onFailure(result.with(false,false));
    callFailureListener(result);
  }
 else {
    result=result.with(true,true);
    onSuccess(result);
    callSuccessListener(result);
  }
  return result;
}
