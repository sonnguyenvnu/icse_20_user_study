private void checkForCRUD(final AbstractApexNode<?> node,final Object data,final String crudMethod){
  final Set<ASTMethodCallExpression> prevCalls=getPreviousMethodCalls(node);
  for (  ASTMethodCallExpression prevCall : prevCalls) {
    collectCRUDMethodLevelChecks(prevCall);
  }
  final ASTMethod wrappingMethod=node.getFirstParentOfType(ASTMethod.class);
  final ASTUserClass wrappingClass=node.getFirstParentOfType(ASTUserClass.class);
  if (wrappingClass != null && Helper.isTestMethodOrClass(wrappingClass) || wrappingMethod != null && Helper.isTestMethodOrClass(wrappingMethod)) {
    return;
  }
  final ASTNewKeyValueObjectExpression newObj=node.getFirstChildOfType(ASTNewKeyValueObjectExpression.class);
  if (newObj != null) {
    final String type=Helper.getFQVariableName(newObj);
    validateCRUDCheckPresent(node,data,crudMethod,type);
  }
  final ASTVariableExpression variable=node.getFirstChildOfType(ASTVariableExpression.class);
  if (variable != null) {
    final String type=varToTypeMapping.get(Helper.getFQVariableName(variable));
    if (type != null) {
      StringBuilder typeCheck=new StringBuilder().append(node.getNode().getDefiningType().getApexName()).append(":").append(type);
      validateCRUDCheckPresent(node,data,crudMethod,typeCheck.toString());
    }
  }
}
