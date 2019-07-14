/** 
 * Extracts the method name of the method call.
 * @param prefix the method call
 * @return the name of the called method
 */
private String getMethodCallName(ASTPrimaryPrefix prefix){
  String result="";
  if (prefix.jjtGetNumChildren() == 1 && prefix.jjtGetChild(0) instanceof ASTName) {
    result=getLastPartOfName(prefix.jjtGetChild(0));
  }
  return result;
}
