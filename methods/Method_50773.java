private void findSelectContainingVariables(AbstractApexNode<?> node){
  final ASTVariableExpression left=node.getFirstChildOfType(ASTVariableExpression.class);
  final ASTBinaryExpression right=node.getFirstChildOfType(ASTBinaryExpression.class);
  if (left != null && right != null) {
    recursivelyCheckForSelect(left,right);
  }
}
