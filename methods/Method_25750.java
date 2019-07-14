@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!BIGDECIMAL_DOUBLE_CONSTRUCTOR.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree arg=getOnlyElement(tree.getArguments());
  if (!floatingPointArgument(arg)) {
    return Description.NO_MATCH;
  }
  return createDescription(arg,state);
}
