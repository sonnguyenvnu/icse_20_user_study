@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  return matchNewClassOrMethodInvocation(ASTHelpers.getSymbol(tree),Comments.findCommentsForArguments(tree,state),tree);
}
