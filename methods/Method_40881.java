/** 
 * Returns a an ExecutionResult with the  {@code result} set, {@code completed} true and {@code success} true.
 */
public static ExecutionResult success(Object result){
  return new ExecutionResult(result,null);
}
