private int expectedArguments(final ASTExpression node){
  int count=0;
  if (node.getFirstDescendantOfType(ASTLiteral.class) != null) {
    count=countPlaceholders(node);
  }
 else   if (node.getFirstDescendantOfType(ASTName.class) != null) {
    final String variableName=node.getFirstDescendantOfType(ASTName.class).getImage();
    final JavaNode parentBlock=node.getFirstParentOfAnyType(ASTMethodOrConstructorDeclaration.class,ASTInitializer.class,ASTLambdaExpression.class);
    if (parentBlock != null) {
      final List<ASTVariableDeclarator> localVariables=parentBlock.findDescendantsOfType(ASTVariableDeclarator.class);
      count=getAmountOfExpectedArguments(variableName,localVariables);
    }
    if (count == 0) {
      final List<ASTFieldDeclaration> fieldlist=node.getFirstParentOfAnyType(ASTClassOrInterfaceBody.class,ASTEnumBody.class).findDescendantsOfType(ASTFieldDeclaration.class);
      final List<ASTVariableDeclarator> fields=new ArrayList<>(fieldlist.size());
      for (      final ASTFieldDeclaration astFieldDeclaration : fieldlist) {
        fields.add(astFieldDeclaration.getFirstChildOfType(ASTVariableDeclarator.class));
      }
      count=getAmountOfExpectedArguments(variableName,fields);
    }
  }
  return count;
}
