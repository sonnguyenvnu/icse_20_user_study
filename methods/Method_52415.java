/** 
 * Recursively resolves the argument again, if the variable initializer is itself a expression. Then checks the expression for being a string literal or array
 * @param data
 * @param firstArgumentExpression
 */
private void validateProperKeyArgument(Object data,ASTPrimaryPrefix firstArgumentExpression){
  if (firstArgumentExpression == null) {
    return;
  }
  ASTName namedVar=firstArgumentExpression.getFirstDescendantOfType(ASTName.class);
  if (namedVar != null) {
    if (namedVar != null && namedVar.getNameDeclaration() instanceof VariableNameDeclaration) {
      VariableNameDeclaration varDecl=(VariableNameDeclaration)namedVar.getNameDeclaration();
      ASTVariableInitializer initializer=varDecl.getAccessNodeParent().getFirstDescendantOfType(ASTVariableInitializer.class);
      if (initializer != null) {
        validateProperKeyArgument(data,initializer.getFirstDescendantOfType(ASTPrimaryPrefix.class));
      }
    }
  }
  ASTArrayInitializer arrayInit=firstArgumentExpression.getFirstDescendantOfType(ASTArrayInitializer.class);
  if (arrayInit != null) {
    addViolation(data,arrayInit);
  }
  ASTLiteral literal=firstArgumentExpression.getFirstDescendantOfType(ASTLiteral.class);
  if (literal != null && literal.isStringLiteral()) {
    addViolation(data,literal);
  }
}
