private boolean isCommentNotWithin(FormalComment n1,Node n2,Node node){
  if (n1 == null || n2 == null || node == null) {
    return true;
  }
  boolean isNotWithinNode2=!(n1.getEndLine() < n2.getEndLine() || n1.getEndLine() == n2.getEndLine() && n1.getEndColumn() < n2.getEndColumn());
  boolean isNotSameClass=node.getFirstParentOfType(ASTClassOrInterfaceBody.class) != n2.getFirstParentOfType(ASTClassOrInterfaceBody.class);
  boolean isNodeWithinNode2=node.getEndLine() < n2.getEndLine() || node.getEndLine() == n2.getEndLine() && node.getEndColumn() < n2.getEndColumn();
  return isNotWithinNode2 || isNotSameClass || isNodeWithinNode2;
}
