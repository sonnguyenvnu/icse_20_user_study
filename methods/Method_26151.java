@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!methodMatcher.matches(tree,state) && !boxingInvocation.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Calling a method that is not annotated with @NoAllocation, calling a varargs" + " method without exactly matching the signature, or passing a primitive value as" + " non-primitive method argument " + COMMON_MESSAGE_SUFFIX).build();
}
