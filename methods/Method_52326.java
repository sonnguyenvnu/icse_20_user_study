/** 
 * Checks, whether the given node is inside a if condition, and if so, whether this is a null check for the given varName.
 * @param enclosingBlock where to search for if statements
 * @param node the node, where the call for the close is done
 * @param varName the variable, that is maybe null-checked
 * @return <code>true</code> if no if condition is involved or if the ifcondition is a null-check.
 */
private boolean nullCheckIfCondition(ASTBlock enclosingBlock,Node node,String varName){
  ASTIfStatement ifStatement=findIfStatement(enclosingBlock,node);
  if (ifStatement != null) {
    try {
      List<?> nodes=ifStatement.findChildNodesWithXPath("Expression/EqualityExpression[@Image='!=']" + "  [PrimaryExpression/PrimaryPrefix/Name[@Image='" + varName + "']]" + "  [PrimaryExpression/PrimaryPrefix/Literal/NullLiteral]");
      return !nodes.isEmpty();
    }
 catch (    JaxenException e) {
      throw new RuntimeException(e);
    }
  }
  return true;
}
