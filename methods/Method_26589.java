@Override public Boolean scan(Tree node,Unifier unifier){
  Iterator<UExpression> iterator=unmatched.iterator();
  while (iterator.hasNext()) {
    if (couldUnify(iterator.next(),node,unifier)) {
      iterator.remove();
      return true;
    }
  }
  for (  UExpression expr : allowed) {
    if (couldUnify(expr,node,unifier)) {
      return true;
    }
  }
  if (node instanceof JCExpression) {
    JCExpression expr=(JCExpression)node;
    for (    UFreeIdent.Key key : Iterables.filter(unifier.getBindings().keySet(),UFreeIdent.Key.class)) {
      JCExpression keyBinding=unifier.getBinding(key);
      if (PlaceholderUnificationVisitor.equivalentExprs(unifier,expr,keyBinding)) {
        return false;
      }
    }
  }
  return firstNonNull(super.scan(node,unifier),true);
}
