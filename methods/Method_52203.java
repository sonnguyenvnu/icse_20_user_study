private static boolean isAfter(Node n1,Node n2){
  return n1.getBeginLine() > n2.getBeginLine() || n1.getBeginLine() == n2.getBeginLine() && n1.getBeginColumn() >= n2.getEndColumn();
}
