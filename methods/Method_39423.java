public Object execute() throws Exception {
  Object target=petiteContainer.getBean(name);
  String methodName=ProxyTarget.targetMethodName();
  Class[] methodArgumentTypes=ProxyTarget.createArgumentsClassArray();
  Object[] methodArguments=ProxyTarget.createArgumentsArray();
  Method targetMethod=target.getClass().getMethod(methodName,methodArgumentTypes);
  Object result=targetMethod.invoke(target,methodArguments);
  return ProxyTarget.returnValue(result);
}
