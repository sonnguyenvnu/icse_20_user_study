@Override public Object intercept(Object obj,Method method,Object[] args,MethodProxy proxy) throws Throwable {
  String methodName=method.getName();
  if (methodName.startsWith("set") && args.length > 0) {
    handleSet(methodName,args);
    return obj;
  }
 else   if (methodName.startsWith("get"))   return handleGet(method);
 else   if (methodName.startsWith("remove"))   return handleRemove(methodName);
  return ObjectProxyHelper.handleObjectMethods(_clazz,obj,method,args);
}
