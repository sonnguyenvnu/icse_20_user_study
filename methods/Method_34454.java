private static MetaHolder.Builder setFallbackMethod(MetaHolder.Builder builder,Class<?> declaringClass,Method commandMethod){
  FallbackMethod fallbackMethod=MethodProvider.getInstance().getFallbackMethod(declaringClass,commandMethod);
  if (fallbackMethod.isPresent()) {
    fallbackMethod.validateReturnType(commandMethod);
    builder.fallbackMethod(fallbackMethod.getMethod()).fallbackExecutionType(ExecutionType.getExecutionType(fallbackMethod.getMethod().getReturnType()));
  }
  return builder;
}
