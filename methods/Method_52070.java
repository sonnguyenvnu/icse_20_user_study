public void analyzeMember(final AbstractJavaAccessNode node,final List<NameOccurrence> occurrences,final ClassScope classScope,final Object data){
  if (!node.isPrivate()) {
    return;
  }
  if (node.isFinal()) {
    for (    final ASTVariableDeclarator varDecl : node.findChildrenOfType(ASTVariableDeclarator.class)) {
      if (varDecl.hasInitializer()) {
        ASTVariableInitializer varInit=varDecl.getInitializer();
        List<ASTExpression> initExpression=varInit.findDescendantsOfType(ASTExpression.class);
        boolean isConstantExpression=true;
        constantCheck:         for (        ASTExpression exp : initExpression) {
          List<ASTPrimaryExpression> primaryExpressions=exp.findDescendantsOfType(ASTPrimaryExpression.class);
          for (          ASTPrimaryExpression expression : primaryExpressions) {
            if (!isCompileTimeConstant(expression)) {
              isConstantExpression=false;
              break constantCheck;
            }
          }
        }
        if (isConstantExpression) {
          cache.add(varDecl.getName());
          return;
        }
      }
    }
  }
  for (  final NameOccurrence no : occurrences) {
    ClassScope usedAtScope=no.getLocation().getScope().getEnclosingScope(ClassScope.class);
    if (!classScope.equals(usedAtScope)) {
      addViolation(data,no.getLocation());
    }
  }
}
