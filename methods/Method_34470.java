/** 
 * Creates closure.
 * @param rootMethodName the name of external method within which closure is created.
 * @param closureObj     the instance of specific anonymous class
 * @return new {@link Closure} instance
 * @throws Exception
 */
Closure createClosure(String rootMethodName,final Object closureObj) throws Exception {
  if (!isClosureCommand(closureObj)) {
    throw new RuntimeException(format(ERROR_TYPE_MESSAGE,rootMethodName,getClosureCommandType().getName()).getMessage());
  }
  Method closureMethod=closureObj.getClass().getMethod(INVOKE_METHOD);
  return new Closure(closureMethod,closureObj);
}
