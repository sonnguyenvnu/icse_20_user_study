/** 
 * ??protobuf???????????????????????
 * @param key        ???key
 * @param clazz      ???
 * @param methodName ???
 */
private void loadProtoClassToCache(String key,Class clazz,String methodName){
  Method pbMethod=null;
  Method[] methods=clazz.getMethods();
  for (  Method method : methods) {
    if (methodName.equals(method.getName())) {
      pbMethod=method;
      break;
    }
  }
  if (pbMethod == null) {
    throw new SofaRpcRuntimeException("Cannot found protobuf method: " + clazz.getName() + "." + methodName);
  }
  Class[] parameterTypes=pbMethod.getParameterTypes();
  if (parameterTypes == null || parameterTypes.length != 1 || isProtoBufMessageObject(parameterTypes[0])) {
    throw new SofaRpcRuntimeException("class based protobuf: " + clazz.getName() + ", only support one protobuf parameter!");
  }
  Class reqClass=parameterTypes[0];
  requestClassCache.put(key,reqClass);
  Class resClass=pbMethod.getReturnType();
  if (resClass == void.class || !isProtoBufMessageClass(resClass)) {
    throw new SofaRpcRuntimeException("class based protobuf: " + clazz.getName() + ", only support return protobuf message!");
  }
  responseClassCache.put(key,resClass);
}
