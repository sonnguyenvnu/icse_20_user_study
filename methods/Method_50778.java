private void flagAuthorizationHeaders(final ASTMethodCallExpression node,Object data){
  if (!Helper.isMethodName(node,SET_HEADER)) {
    return;
  }
  final ASTBinaryExpression binaryNode=node.getFirstChildOfType(ASTBinaryExpression.class);
  if (binaryNode != null) {
    runChecks(binaryNode,data);
  }
  runChecks(node,data);
}
