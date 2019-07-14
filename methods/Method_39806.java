@Override public boolean apply(final MethodInfo methodInfo){
  return methodInfo.isTopLevelMethod() && methodInfo.isPublicMethod();
}
