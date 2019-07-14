/** 
 * Determine if during the variable initializer calls to ".append" are done.
 * @param node
 * @return
 */
private int checkInitializerExpressions(ASTVariableDeclaratorId node){
  ASTVariableInitializer initializer=node.jjtGetParent().getFirstChildOfType(ASTVariableInitializer.class);
  ASTPrimaryExpression primary=initializer.getFirstDescendantOfType(ASTPrimaryExpression.class);
  int result=0;
  boolean previousWasAppend=false;
  for (int i=0; i < primary.jjtGetNumChildren(); i++) {
    Node child=primary.jjtGetChild(i);
    if (child.jjtGetNumChildren() > 0 && child.jjtGetChild(0) instanceof ASTAllocationExpression) {
      continue;
    }
    if (child instanceof ASTPrimarySuffix) {
      ASTPrimarySuffix suffix=(ASTPrimarySuffix)child;
      if (suffix.jjtGetNumChildren() == 0 && suffix.hasImageEqualTo("append")) {
        previousWasAppend=true;
      }
 else       if (suffix.jjtGetNumChildren() > 0 && previousWasAppend) {
        previousWasAppend=false;
        ASTLiteral literal=suffix.getFirstDescendantOfType(ASTLiteral.class);
        if (literal != null && literal.isStringLiteral()) {
          result++;
        }
 else {
          break;
        }
      }
    }
  }
  return result;
}
