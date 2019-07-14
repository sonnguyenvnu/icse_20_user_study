/** 
 * Reads method's async flag.
 */
private boolean parseMethodAsyncFlag(final Method actionMethod){
  return actionMethod.getAnnotation(Async.class) != null;
}
