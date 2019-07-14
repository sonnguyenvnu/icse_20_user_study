/** 
 * Checks to see if there is string concatenation in the node. This method checks if it's additive with respect to the append method only.
 * @param n Node to check
 * @return true if the node has an additive expression (i.e. "Hello " +Const.WORLD)
 */
private boolean isAdditive(Node n){
  List<ASTAdditiveExpression> lstAdditive=n.findDescendantsOfType(ASTAdditiveExpression.class);
  if (lstAdditive.isEmpty()) {
    return false;
  }
  for (int ix=0; ix < lstAdditive.size(); ix++) {
    ASTAdditiveExpression expr=lstAdditive.get(ix);
    if (expr.getParentsOfType(ASTArgumentList.class).size() != 1) {
      return false;
    }
  }
  return true;
}
