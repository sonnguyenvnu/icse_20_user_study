private void handleFlowNode(Stack<Node> stack,Set<String> localSimpleDateFormatNames,DataFlowNode flowNode){
  if (flowNode instanceof StartOrEndDataFlowNode || flowNode.getNode() instanceof ASTMethodDeclaration) {
    return;
  }
  if (flowNode.getNode() instanceof ASTVariableDeclarator) {
    ASTVariableDeclarator variableDeclarator=(ASTVariableDeclarator)flowNode.getNode();
    if (variableDeclarator.getType() == SimpleDateFormat.class) {
      ASTVariableDeclaratorId variableDeclaratorId=variableDeclarator.getFirstChildOfType(ASTVariableDeclaratorId.class);
      localSimpleDateFormatNames.add(variableDeclaratorId.getImage());
      return;
    }
  }
  if (flowNode.getNode() instanceof ASTStatementExpression) {
    ASTStatementExpression statementExpression=(ASTStatementExpression)flowNode.getNode();
    if (isLockStatementExpression(statementExpression)) {
      stack.push(flowNode.getNode());
      return;
    }
 else     if (isUnLockStatementExpression(statementExpression)) {
      while (!stack.isEmpty()) {
        Node node=stack.pop();
        if (isLockNode(node)) {
          break;
        }
      }
      return;
    }
  }
  AbstractJavaNode javaNode=(AbstractJavaNode)flowNode.getNode();
  ASTPrimaryExpression flowPrimaryExpression=javaNode.getFirstDescendantOfType(ASTPrimaryExpression.class);
  if (flowPrimaryExpression == null) {
    return;
  }
  if (flowPrimaryExpression.getFirstParentOfType(ASTSynchronizedStatement.class) != null) {
    return;
  }
  if (!isStaticSimpleDateFormatCall(flowPrimaryExpression,localSimpleDateFormatNames)) {
    return;
  }
  stack.push(flowPrimaryExpression);
}
