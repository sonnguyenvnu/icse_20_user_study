@SuppressWarnings("PMD.CompareObjectsWithEquals") @Override public Object visit(ASTFieldDeclaration node,Object data){
  boolean checkInnerClasses=getProperty(CHECK_INNER_CLASSES);
  boolean disallowNotAssignment=getProperty(DISALLOW_NOT_ASSIGNMENT);
  if (node.isPrivate() && !node.isStatic() && !hasClassLombokAnnotation() && !hasIgnoredAnnotation(node)) {
    for (    ASTVariableDeclarator declarator : node.findChildrenOfType(ASTVariableDeclarator.class)) {
      ASTVariableDeclaratorId declaration=(ASTVariableDeclaratorId)declarator.jjtGetChild(0);
      List<NameOccurrence> usages=declaration.getUsages();
      Node decl=null;
      boolean violation=true;
      for (int ix=0; ix < usages.size(); ix++) {
        NameOccurrence no=usages.get(ix);
        Node location=no.getLocation();
        ASTPrimaryExpression primaryExpressionParent=location.getFirstParentOfType(ASTPrimaryExpression.class);
        if (ix == 0 && !disallowNotAssignment) {
          if (primaryExpressionParent.getFirstParentOfType(ASTIfStatement.class) != null) {
            violation=false;
            break;
          }
          Node potentialStatement=primaryExpressionParent.jjtGetParent();
          boolean assignmentToField=no.getImage().equals(location.getImage());
          if (!assignmentToField || !isInAssignment(potentialStatement)) {
            violation=false;
            break;
          }
 else {
            if (usages.size() > ix + 1) {
              Node secondUsageLocation=usages.get(ix + 1).getLocation();
              List<ASTStatementExpression> parentStatements=secondUsageLocation.getParentsOfType(ASTStatementExpression.class);
              for (              ASTStatementExpression statementExpression : parentStatements) {
                if (statementExpression != null && statementExpression.equals(potentialStatement)) {
                  violation=false;
                  break;
                }
              }
            }
          }
        }
        if (!checkInnerClasses) {
          ASTClassOrInterfaceDeclaration clazz=location.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
          if (clazz != null && clazz.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class) != null) {
            violation=false;
            break;
          }
        }
        if (primaryExpressionParent.jjtGetParent() instanceof ASTSynchronizedStatement) {
          violation=false;
          break;
        }
        if (location.getFirstParentOfType(ASTLambdaExpression.class) != null) {
          violation=false;
          break;
        }
        Node method=location.getFirstParentOfType(ASTMethodDeclaration.class);
        if (method == null) {
          method=location.getFirstParentOfType(ASTConstructorDeclaration.class);
          if (method == null) {
            method=location.getFirstParentOfType(ASTInitializer.class);
            if (method == null) {
              continue;
            }
          }
        }
        if (decl == null) {
          decl=method;
          continue;
        }
 else         if (decl != method && decl.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class) == method.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class)) {
          violation=false;
          break;
        }
      }
      if (violation && !usages.isEmpty()) {
        addViolation(data,node,new Object[]{declaration.getImage()});
      }
    }
  }
  return data;
}
