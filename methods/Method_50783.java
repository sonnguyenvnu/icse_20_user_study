@Override public Object visit(ASTReturnStatement node,Object data){
  ASTBinaryExpression binaryExpression=node.getFirstChildOfType(ASTBinaryExpression.class);
  if (binaryExpression != null) {
    processBinaryExpression(binaryExpression,data);
  }
  ASTMethodCallExpression methodCall=node.getFirstChildOfType(ASTMethodCallExpression.class);
  if (methodCall != null) {
    String retType=getReturnType(node);
    if ("string".equalsIgnoreCase(retType)) {
      processInlineMethodCalls(methodCall,data,true);
    }
  }
  List<ASTVariableExpression> nodes=node.findChildrenOfType(ASTVariableExpression.class);
  for (  ASTVariableExpression varExpression : nodes) {
    if (urlParameterStrings.contains(Helper.getFQVariableName(varExpression))) {
      addViolation(data,nodes.get(0));
    }
  }
  return data;
}
