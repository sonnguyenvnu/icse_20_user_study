private static int getBeanPropertySetterPrefixLength(final Method method){
  if (isObjectMethod(method)) {
    return 0;
  }
  String methodName=method.getName();
  Class[] paramTypes=method.getParameterTypes();
  if (methodName.startsWith(METHOD_SET_PREFIX)) {
    if (paramTypes.length == 1) {
      return 3;
    }
  }
  return 0;
}
