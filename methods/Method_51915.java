/** 
 * Returns true if the variable declared by this node is declared final. Doesn't account for the "effectively-final" nuance. Resource declarations are implicitly final.
 */
public boolean isFinal(){
  if (isResourceDeclaration()) {
    return true;
  }
 else   if (isLambdaParamWithNoType()) {
    return false;
  }
  if (jjtGetParent() instanceof ASTFormalParameter) {
    return ((ASTFormalParameter)jjtGetParent()).isFinal();
  }
  Node grandpa=getNthParent(2);
  if (grandpa instanceof ASTLocalVariableDeclaration) {
    return ((ASTLocalVariableDeclaration)grandpa).isFinal();
  }
 else   if (grandpa instanceof ASTFieldDeclaration) {
    return ((ASTFieldDeclaration)grandpa).isFinal();
  }
  throw new IllegalStateException("All cases should be handled");
}
