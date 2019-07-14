private static final IllegalStateException malformedMethodInvocationTree(MethodInvocationTree tree){
  return new IllegalStateException(String.format("Couldn't replace the method name in %s.",tree));
}
