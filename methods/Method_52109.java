private boolean isEmptyArray(String varName,ASTAnyTypeDeclaration typeDeclaration){
  final List<ASTFieldDeclaration> fds=typeDeclaration.findDescendantsOfType(ASTFieldDeclaration.class);
  if (fds != null) {
    for (    ASTFieldDeclaration fd : fds) {
      final ASTVariableDeclaratorId vid=fd.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
      if (vid != null && vid.hasImageEqualTo(varName)) {
        ASTVariableInitializer initializer=fd.getFirstDescendantOfType(ASTVariableInitializer.class);
        if (initializer != null && initializer.jjtGetNumChildren() == 1) {
          Node child=initializer.jjtGetChild(0);
          if (child instanceof ASTArrayInitializer && child.jjtGetNumChildren() == 0) {
            return true;
          }
 else           if (child instanceof ASTExpression) {
            try {
              List<? extends Node> arrayAllocation=child.findChildNodesWithXPath("./PrimaryExpression/PrimaryPrefix/AllocationExpression/ArrayDimsAndInits/Expression/PrimaryExpression/PrimaryPrefix/Literal[@IntLiteral=\"true\"][@Image=\"0\"]");
              if (arrayAllocation != null && arrayAllocation.size() == 1) {
                return true;
              }
            }
 catch (            JaxenException e) {
              return false;
            }
          }
        }
      }
    }
  }
  return false;
}
