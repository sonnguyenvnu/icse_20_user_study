private void findSafeVariables(AbstractApexNode<?> var){
  ASTMethodCallExpression methodCall=var.getFirstChildOfType(ASTMethodCallExpression.class);
  if (methodCall != null && Helper.isMethodName(methodCall,BLOB,VALUE_OF)) {
    ASTVariableExpression variable=var.getFirstChildOfType(ASTVariableExpression.class);
    if (variable != null) {
      potentiallyStaticBlob.add(Helper.getFQVariableName(variable));
    }
  }
}
