private void findInsecureEndpoints(AbstractApexNode<?> node){
  ASTVariableExpression variableNode=node.getFirstChildOfType(ASTVariableExpression.class);
  findInnerInsecureEndpoints(node,variableNode);
  ASTBinaryExpression binaryNode=node.getFirstChildOfType(ASTBinaryExpression.class);
  if (binaryNode != null) {
    findInnerInsecureEndpoints(binaryNode,variableNode);
  }
}
