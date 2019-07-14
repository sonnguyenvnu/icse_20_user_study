@Override public boolean apply(final MethodInfo methodInfo){
  if (methodInfo.hasReturnValue() && (methodInfo.matchMethodName("get*") || (methodInfo.matchMethodName("is*"))) && methodInfo.hasNoArguments()) {
    return false;
  }
  if (methodInfo.matchMethodName("set*") && methodInfo.hasOneArgument()) {
    return false;
  }
  return methodInfo.isPublicMethod();
}
