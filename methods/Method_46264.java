@Override public void registerProcessor(ProviderConfig providerConfig,Invoker instance){
  String serviceName=getUniqueName(providerConfig);
  serverHandler.getInvokerMap().put(serviceName,instance);
  Class itfClass=providerConfig.getProxyClass();
  HashMap<String,Method> methodsLimit=new HashMap<String,Method>(16);
  for (  Method method : itfClass.getMethods()) {
    String methodName=method.getName();
    if (methodsLimit.containsKey(methodName)) {
      throw new SofaRpcRuntimeException("Method with same name \"" + itfClass.getName() + "." + methodName + "\" exists ! The usage of overloading method is deprecated.");
    }
    methodsLimit.put(methodName,method);
  }
  for (  Map.Entry<String,Method> entry : methodsLimit.entrySet()) {
    ReflectCache.putMethodCache(serviceName,entry.getValue());
    ReflectCache.putMethodSigsCache(serviceName,entry.getKey(),ClassTypeUtils.getTypeStrs(entry.getValue().getParameterTypes(),true));
  }
}
