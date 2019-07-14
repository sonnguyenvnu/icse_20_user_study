private void reportStrings(ASTMethodCallExpression m,Object data){
  final HashSet<ASTVariableExpression> setOfSafeVars=new HashSet<>();
  final List<ASTStandardCondition> conditions=m.findDescendantsOfType(ASTStandardCondition.class);
  for (  ASTStandardCondition c : conditions) {
    List<ASTVariableExpression> vars=c.findDescendantsOfType(ASTVariableExpression.class);
    setOfSafeVars.addAll(vars);
  }
  final List<ASTBinaryExpression> binaryExpr=m.findChildrenOfType(ASTBinaryExpression.class);
  for (  ASTBinaryExpression b : binaryExpr) {
    List<ASTVariableExpression> vars=b.findDescendantsOfType(ASTVariableExpression.class);
    for (    ASTVariableExpression v : vars) {
      String fqName=Helper.getFQVariableName(v);
      if (selectContainingVariables.containsKey(fqName)) {
        boolean isLiteral=selectContainingVariables.get(fqName);
        if (isLiteral) {
          continue;
        }
      }
      if (setOfSafeVars.contains(v) || safeVariables.contains(fqName)) {
        continue;
      }
      final ASTMethodCallExpression parentCall=v.getFirstParentOfType(ASTMethodCallExpression.class);
      boolean isSafeMethod=Helper.isMethodName(parentCall,STRING,ESCAPE_SINGLE_QUOTES) || Helper.isMethodName(parentCall,STRING,JOIN);
      if (!isSafeMethod) {
        addViolation(data,v);
      }
    }
  }
}
