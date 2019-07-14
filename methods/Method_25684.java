@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  MethodSymbol symbol=ASTHelpers.getSymbol(tree);
  if (symbol == null) {
    return Description.NO_MATCH;
  }
  if (Matchers.AUTOVALUE_CONSTRUCTOR.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  return visitNewClassOrMethodInvocation(InvocationInfo.createFromNewClass(tree,symbol,state));
}
