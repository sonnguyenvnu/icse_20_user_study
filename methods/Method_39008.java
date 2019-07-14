/** 
 * Invokes the action and returns action result value object. Invokes all interceptors before and after action invocation.
 */
public Object invoke() throws Exception {
  return executionArray[executionIndex++].apply(this);
}
