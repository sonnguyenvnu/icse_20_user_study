private void addForExpressionNode(Node node,Structure dataFlow){
  ASTForStatement parent=(ASTForStatement)node.jjtGetParent();
  boolean hasExpressionChild=false;
  boolean hasForInitNode=false;
  boolean hasForUpdateNode=false;
  for (int i=0; i < parent.jjtGetNumChildren(); i++) {
    if (parent.jjtGetChild(i) instanceof ASTExpression) {
      hasExpressionChild=true;
    }
 else     if (parent.jjtGetChild(i) instanceof ASTForUpdate) {
      hasForUpdateNode=true;
    }
 else     if (parent.jjtGetChild(i) instanceof ASTForInit) {
      hasForInitNode=true;
    }
  }
  if (!hasExpressionChild) {
    if (node instanceof ASTForInit) {
      dataFlow.createNewNode(node);
      dataFlow.pushOnStack(NodeType.FOR_EXPR,dataFlow.getLast());
      tryToLog(NodeType.FOR_EXPR,node);
    }
 else     if (node instanceof ASTForUpdate) {
      if (!hasForInitNode) {
        dataFlow.createNewNode(node);
        dataFlow.pushOnStack(NodeType.FOR_EXPR,dataFlow.getLast());
        tryToLog(NodeType.FOR_EXPR,node);
      }
    }
 else     if (node instanceof ASTStatement) {
      if (!hasForInitNode && !hasForUpdateNode) {
        dataFlow.createNewNode(node);
        dataFlow.pushOnStack(NodeType.FOR_EXPR,dataFlow.getLast());
        tryToLog(NodeType.FOR_EXPR,node);
      }
    }
  }
}
