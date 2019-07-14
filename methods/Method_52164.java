/** 
 * Checks whether two nodes define the same subtree, up to the renaming of one local variable.
 * @param node1          the first node to check
 * @param node2          the second node to check
 * @param exceptionName1 the first exception variable name
 * @param exceptionName2 the second exception variable name
 */
private boolean hasSameSubTree(Node node1,Node node2,String exceptionName1,String exceptionName2){
  if (node1 == null && node2 == null) {
    return true;
  }
 else   if (node1 == null || node2 == null) {
    return false;
  }
  if (node1.jjtGetNumChildren() != node2.jjtGetNumChildren()) {
    return false;
  }
  for (int num=0; num < node1.jjtGetNumChildren(); num++) {
    if (!basicEquivalence(node1.jjtGetChild(num),node2.jjtGetChild(num),exceptionName1,exceptionName2)) {
      return false;
    }
    if (!hasSameSubTree(node1.jjtGetChild(num),node2.jjtGetChild(num),exceptionName1,exceptionName2)) {
      return false;
    }
  }
  return true;
}
