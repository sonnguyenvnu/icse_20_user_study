private Class<?> resolveReturnValueClass(RestMethodMetadata restMethodMetadata){
  String returnClassName=restMethodMetadata.getMethod().getReturnType();
  return ClassUtils.resolveClassName(returnClassName,classLoader);
}
