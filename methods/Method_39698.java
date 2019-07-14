/** 
 * Looks up for method in target object and invokes it using reflection.
 */
public Object execute() throws Exception {
  String methodName=ProxyTarget.targetMethodName();
  Class[] argTypes=ProxyTarget.createArgumentsClassArray();
  Object[] args=ProxyTarget.createArgumentsArray();
  Class type=_target.getClass();
  Method method=type.getMethod(methodName,argTypes);
  ClassLoader contextClassLoader=Thread.currentThread().getContextClassLoader();
  Object result;
  try {
    Thread.currentThread().setContextClassLoader(type.getClassLoader());
    result=method.invoke(_target,args);
  }
  finally {
    Thread.currentThread().setContextClassLoader(contextClassLoader);
  }
  return ProxyTarget.returnValue(result);
}
