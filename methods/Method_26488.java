@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!SET_DEFAULT.matches(tree,state)) {
    return NO_MATCH;
  }
  Type type=ASTHelpers.getType(tree.getArguments().get(0));
  if (type == null) {
    return NO_MATCH;
  }
  Type classType=state.getTypes().asSuper(type,state.getSymtab().classType.asElement());
  if (classType == null || classType.getTypeArguments().isEmpty()) {
    return NO_MATCH;
  }
  String defaultTypeName=getOnlyElement(classType.getTypeArguments()).asElement().getQualifiedName().toString();
  if (!DEFAULTS.containsKey(defaultTypeName)) {
    return NO_MATCH;
  }
  Matcher<ExpressionTree> defaultType=DEFAULTS.get(defaultTypeName);
  if (!defaultType.matches(tree.getArguments().get(1),state)) {
    return NO_MATCH;
  }
  Description.Builder description=buildDescription(tree);
  ExpressionTree receiver=ASTHelpers.getReceiver(tree);
  Tree ancestor=state.getPath().getParentPath().getLeaf();
  if (ancestor instanceof ExpressionStatementTree) {
    description.addFix(SuggestedFix.delete(ancestor));
  }
 else   if (receiver != null) {
    description.addFix(SuggestedFix.replace(state.getEndPosition(receiver),state.getEndPosition(tree),""));
  }
  return description.build();
}
