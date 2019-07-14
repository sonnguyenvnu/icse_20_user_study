private boolean isCommentBefore(Comment n1,Node n2){
  return n1.getEndLine() < n2.getBeginLine() || n1.getEndLine() == n2.getBeginLine() && n1.getEndColumn() < n2.getBeginColumn();
}
