private static boolean isPrimitive(Node parent){
  boolean result=false;
  if (parent instanceof ASTPrimaryExpression && parent.jjtGetNumChildren() == 1) {
    Node child=parent.jjtGetChild(0);
    if (child instanceof ASTPrimaryPrefix && child.jjtGetNumChildren() == 1) {
      Node gc=child.jjtGetChild(0);
      if (gc instanceof ASTName) {
        ASTName name=(ASTName)gc;
        NameDeclaration nd=name.getNameDeclaration();
        if (nd instanceof VariableNameDeclaration && ((VariableNameDeclaration)nd).isPrimitiveType()) {
          result=true;
        }
      }
 else       if (gc instanceof ASTLiteral) {
        result=!((ASTLiteral)gc).isStringLiteral();
      }
    }
  }
  return result;
}
