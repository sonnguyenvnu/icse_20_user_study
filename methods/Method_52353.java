private boolean isAssignmentToFinalField(ASTStatementExpression n){
  ASTName name=n.getFirstDescendantOfType(ASTName.class);
  return name != null && name.getNameDeclaration() instanceof VariableNameDeclaration && ((AccessNode)((VariableNameDeclaration)name.getNameDeclaration()).getAccessNodeParent()).isFinal();
}
