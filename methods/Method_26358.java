@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  checkInvocation(tree,((JCNewClass)tree).constructorType,state,((JCNewClass)tree).constructor);
  ImmutableAnalysis analysis=createImmutableAnalysis(state);
  Violation info=analysis.checkInstantiation(ASTHelpers.getSymbol(tree.getIdentifier()).getTypeParameters(),ASTHelpers.getType(tree).getTypeArguments());
  if (info.isPresent()) {
    state.reportMatch(buildDescription(tree).setMessage(info.message()).build());
  }
  return NO_MATCH;
}
