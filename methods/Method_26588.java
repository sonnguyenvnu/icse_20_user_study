private boolean couldUnify(UExpression expr,Tree tree,Unifier unifier){
  return expr.unify(tree,unifier.fork()).first().isPresent();
}
