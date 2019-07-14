private boolean thirdChildHasDottedName(Node primaryExpression){
  Node thirdChild=primaryExpression.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
  return thirdChild instanceof ASTName && ((ASTName)thirdChild).getImage().indexOf('.') == -1;
}
