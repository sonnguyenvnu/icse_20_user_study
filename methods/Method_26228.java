@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (SAFEHTML_PRIVATE_FIELD_ACCESS.matches(tree,state)) {
    return buildErrorMessage(tree,SAFEHTML_LINK);
  }
  return Description.NO_MATCH;
}
