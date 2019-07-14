@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  JCExpression receiverTree=(JCExpression)tree.getEnclosingExpression();
  if (receiverTree != null) {
    Description result=checkExpression(receiverTree,state,qual -> String.format("Outer object %s for %s is %s null",receiverTree,tree.getIdentifier(),qual));
    if (result != null) {
      return result;
    }
  }
  return checkCallArguments(tree.getArguments(),getSymbol(tree),state);
}
