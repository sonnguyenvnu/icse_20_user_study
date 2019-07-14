/** 
 * Returns true for the following violations: <pre> StringBuffer sb = new StringBuffer(&quot;some string&quot;); if (sb.toString().equals(&quot;&quot;)) { // this is a violation } if (sb.toString().length() == 0) { // this is a violation } if (sb.length() == 0) { // this is ok } </pre>
 */
private boolean isViolation(ASTName decl){
  Node parent=decl.jjtGetParent().jjtGetParent();
  if (parent.jjtGetNumChildren() == 4) {
    if (parent.jjtGetChild(0).getFirstChildOfType(ASTName.class).getImage().endsWith(".toString")) {
      return isEqualsViolation(parent) || isLengthViolation(parent);
    }
  }
  return false;
}
