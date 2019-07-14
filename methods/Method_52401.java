private boolean isAdditive(Node n){
  ASTAdditiveExpression add=n.getFirstDescendantOfType(ASTAdditiveExpression.class);
  return add != null && add.getNthParent(4) == n;
}
