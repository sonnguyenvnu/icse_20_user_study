private static int getBeanPropertyGetterPrefixLength(final Method method){
  if (isObjectMethod(method)) {
    return 0;
  }
  String methodName=method.getName();
  Class returnType=method.getReturnType();
  Class[] paramTypes=method.getParameterTypes();
  if (methodName.startsWith(METHOD_GET_PREFIX)) {
    if ((returnType != null) && (paramTypes.length == 0)) {
      return 3;
    }
  }
 else   if (methodName.startsWith(METHOD_IS_PREFIX)) {
    if ((returnType != null) && (paramTypes.length == 0)) {
      return 2;
    }
  }
  return 0;
}
