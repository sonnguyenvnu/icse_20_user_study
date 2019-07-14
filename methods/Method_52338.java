/** 
 * @return A method call on the class passed in, or null if no method callis found. TODO Need a better way to match the class and package name to the actual method being called.
 */
private static MethodInvocation findMethod(ASTPrimaryExpression node,String className){
  if (node.jjtGetNumChildren() > 0 && node.jjtGetChild(0).jjtGetNumChildren() > 0 && node.jjtGetChild(0).jjtGetChild(0) instanceof ASTLiteral) {
    return null;
  }
  MethodInvocation meth=MethodInvocation.getMethod(node);
  boolean found=false;
  if (meth != null) {
    if (meth.getReferenceNames().isEmpty() && !meth.isSuper()) {
      List<String> packClass=meth.getQualifierNames();
      if (!packClass.isEmpty()) {
        for (        String name : packClass) {
          if (name.equals(className)) {
            found=true;
            break;
          }
        }
      }
 else {
        found=true;
      }
    }
  }
  return found ? meth : null;
}
