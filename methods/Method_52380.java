/** 
 * Determine if the constructor contains (or ends with) a String Literal
 * @param node
 * @return 1 if the constructor contains string argument, else 0
 */
private int checkConstructor(ASTVariableDeclaratorId node,Object data){
  Node parent=node.jjtGetParent();
  if (parent.jjtGetNumChildren() >= 2) {
    ASTAllocationExpression allocationExpression=parent.jjtGetChild(1).getFirstDescendantOfType(ASTAllocationExpression.class);
    ASTArgumentList list=null;
    if (allocationExpression != null) {
      list=allocationExpression.getFirstDescendantOfType(ASTArgumentList.class);
    }
    if (list != null) {
      ASTLiteral literal=list.getFirstDescendantOfType(ASTLiteral.class);
      if (!isAdditive(list) && literal != null && literal.isStringLiteral()) {
        return 1;
      }
      return processAdditive(data,0,list,node);
    }
  }
  return 0;
}
