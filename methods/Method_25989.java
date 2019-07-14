@Override public Description matchMethodInvocation(MethodInvocationTree tree,final VisitorState state){
  if (!METHOD_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  String value=ASTHelpers.constValue(getOnlyElement(tree.getArguments()),String.class);
  if (value == null) {
    return Description.NO_MATCH;
  }
  if (isValidID(value)) {
    return Description.NO_MATCH;
  }
  return describeMatch(tree);
}
