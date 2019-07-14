private static SetView<String> unhandledCases(SwitchTree tree,TypeSymbol switchType){
  Set<String> handledCases=tree.getCases().stream().map(CaseTree::getExpression).filter(IdentifierTree.class::isInstance).map(p -> ((IdentifierTree)p).getName().toString()).collect(toImmutableSet());
  return Sets.difference(ASTHelpers.enumValues(switchType),handledCases);
}
