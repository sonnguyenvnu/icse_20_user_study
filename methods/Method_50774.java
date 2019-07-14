private void recursivelyCheckForSelect(final ASTVariableExpression var,final ASTBinaryExpression node){
  final ASTBinaryExpression right=node.getFirstChildOfType(ASTBinaryExpression.class);
  if (right != null) {
    recursivelyCheckForSelect(var,right);
  }
  final ASTVariableExpression concatenatedVar=node.getFirstChildOfType(ASTVariableExpression.class);
  boolean isSafeVariable=false;
  if (concatenatedVar != null) {
    if (safeVariables.contains(Helper.getFQVariableName(concatenatedVar))) {
      isSafeVariable=true;
    }
  }
  final ASTMethodCallExpression methodCall=node.getFirstChildOfType(ASTMethodCallExpression.class);
  if (methodCall != null) {
    if (Helper.isMethodName(methodCall,STRING,ESCAPE_SINGLE_QUOTES)) {
      isSafeVariable=true;
    }
  }
  final ASTLiteralExpression literal=node.getFirstChildOfType(ASTLiteralExpression.class);
  if (literal != null) {
    if (literal.isString()) {
      if (SELECT_PATTERN.matcher(literal.getImage()).matches()) {
        if (!isSafeVariable) {
          selectContainingVariables.put(Helper.getFQVariableName(var),Boolean.FALSE);
        }
 else {
          safeVariables.add(Helper.getFQVariableName(var));
        }
      }
    }
  }
 else {
    if (!isSafeVariable) {
      selectContainingVariables.put(Helper.getFQVariableName(var),Boolean.FALSE);
    }
  }
}
