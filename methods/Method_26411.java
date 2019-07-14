@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!CONSTRUCTOR_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree argument=getOnlyElement(tree.getArguments());
  if (!ASTHelpers.isSameType(ASTHelpers.getType(argument),ASTHelpers.getType(tree.getIdentifier()),state)) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(String.format("Use of %s is a no-op and is not allowed.",ASTHelpers.getSymbol(tree))).addFix(SuggestedFix.replace(tree,state.getSourceForNode(argument))).build();
}
