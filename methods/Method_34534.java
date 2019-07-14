public static boolean isGenericReturnType(Method method){
  return isParametrizedType(method.getGenericReturnType()) || isTypeVariable(method.getGenericReturnType());
}
