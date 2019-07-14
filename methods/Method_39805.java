@Override public boolean apply(final MethodInfo methodInfo){
  return methodInfo.isPublicMethod() && methodInfo.matchMethodName("set*") && methodInfo.hasOneArgument();
}
