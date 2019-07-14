private void findSafeLiterals(AbstractApexNode<?> node){
  ASTBinaryExpression binaryExp=node.getFirstChildOfType(ASTBinaryExpression.class);
  if (binaryExp != null) {
    findSafeLiterals(binaryExp);
  }
  ASTLiteralExpression literal=node.getFirstChildOfType(ASTLiteralExpression.class);
  if (literal != null) {
    int index=literal.jjtGetChildIndex();
    if (index == 0) {
      if (node instanceof ASTVariableDeclaration) {
        addVariable((ASTVariableDeclaration)node);
      }
 else       if (node instanceof ASTBinaryExpression) {
        ASTVariableDeclaration parent=node.getFirstParentOfType(ASTVariableDeclaration.class);
        if (parent != null) {
          addVariable(parent);
        }
        ASTAssignmentExpression assignment=node.getFirstParentOfType(ASTAssignmentExpression.class);
        if (assignment != null) {
          ASTVariableExpression var=assignment.getFirstChildOfType(ASTVariableExpression.class);
          if (var != null) {
            addVariable(var);
          }
        }
      }
    }
  }
 else {
    if (node instanceof ASTField) {
      ASTField field=(ASTField)node;
      if ("String".equalsIgnoreCase(field.getType())) {
        if (field.getValue() != null) {
          listOfStringLiteralVariables.add(Helper.getFQVariableName(field));
        }
      }
    }
  }
}
