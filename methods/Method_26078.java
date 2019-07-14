@Override public Description matchSwitch(SwitchTree tree,VisitorState state){
  Type switchType=ASTHelpers.getType(tree.getExpression());
  if (switchType.asElement().getKind() != ElementKind.ENUM) {
    return Description.NO_MATCH;
  }
  if (tree.getCases().stream().anyMatch(c -> c.getExpression() == null)) {
    return Description.NO_MATCH;
  }
  ImmutableSet<String> handled=tree.getCases().stream().map(CaseTree::getExpression).filter(IdentifierTree.class::isInstance).map(e -> ((IdentifierTree)e).getName().toString()).collect(toImmutableSet());
  Set<String> unhandled=Sets.difference(ASTHelpers.enumValues(switchType.asElement()),handled);
  if (unhandled.isEmpty()) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(buildMessage(unhandled)).build();
}
