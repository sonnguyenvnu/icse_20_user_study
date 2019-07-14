@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MOCK_METHOD.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  if (state.getPath().getParentPath().getLeaf().getKind() != Tree.Kind.EXPRESSION_STATEMENT) {
    return Description.NO_MATCH;
  }
  String message=String.format(MESSAGE_FORMAT,state.getSourceForNode(tree));
  Description.Builder builder=buildDescription(tree).setMessage(message);
  buildFix(builder,tree,state);
  return builder.build();
}
