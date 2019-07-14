@Override public boolean apply(final MethodInfo methodInfo){
  return methodInfo.isPublicMethod() && methodInfo.hasReturnValue() && (methodInfo.matchMethodName("get*") || (methodInfo.matchMethodName("is*"))) && methodInfo.hasNoArguments();
}
