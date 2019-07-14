private boolean inLoopOrTry(Node node){
  return node.getFirstParentOfType(ASTTryStatement.class) != null || node.getFirstParentOfType(ASTForStatement.class) != null || node.getFirstParentOfType(ASTWhileStatement.class) != null || node.getFirstParentOfType(ASTDoStatement.class) != null;
}
