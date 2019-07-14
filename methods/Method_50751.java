private void checkForAccessibility(final ASTSoqlExpression node,Object data){
  final boolean isCount=node.getNode().getCanonicalQuery().startsWith("SELECT COUNT()");
  final Set<String> typesFromSOQL=getTypesFromSOQLQuery(node);
  final Set<ASTMethodCallExpression> prevCalls=getPreviousMethodCalls(node);
  for (  ASTMethodCallExpression prevCall : prevCalls) {
    collectCRUDMethodLevelChecks(prevCall);
  }
  boolean isGetter=false;
  String returnType=null;
  final ASTMethod wrappingMethod=node.getFirstParentOfType(ASTMethod.class);
  final ASTUserClass wrappingClass=node.getFirstParentOfType(ASTUserClass.class);
  if (isCount || wrappingClass != null && Helper.isTestMethodOrClass(wrappingClass) || wrappingMethod != null && Helper.isTestMethodOrClass(wrappingMethod)) {
    return;
  }
  if (wrappingMethod != null) {
    isGetter=isMethodAGetter(wrappingMethod);
    returnType=getReturnType(wrappingMethod);
  }
  final ASTVariableDeclaration variableDecl=node.getFirstParentOfType(ASTVariableDeclaration.class);
  if (variableDecl != null) {
    String type=variableDecl.getNode().getLocalInfo().getType().getApexName();
    type=getSimpleType(type);
    StringBuilder typeCheck=new StringBuilder().append(variableDecl.getNode().getDefiningType().getApexName()).append(":").append(type);
    if (!isGetter) {
      if (typesFromSOQL.isEmpty()) {
        validateCRUDCheckPresent(node,data,ANY,typeCheck.toString());
      }
 else {
        for (        String typeFromSOQL : typesFromSOQL) {
          validateCRUDCheckPresent(node,data,ANY,typeFromSOQL);
        }
      }
    }
  }
  final ASTAssignmentExpression assignment=node.getFirstParentOfType(ASTAssignmentExpression.class);
  if (assignment != null) {
    final ASTVariableExpression variable=assignment.getFirstChildOfType(ASTVariableExpression.class);
    if (variable != null) {
      String variableWithClass=Helper.getFQVariableName(variable);
      if (varToTypeMapping.containsKey(variableWithClass)) {
        String type=varToTypeMapping.get(variableWithClass);
        if (!isGetter) {
          if (typesFromSOQL.isEmpty()) {
            validateCRUDCheckPresent(node,data,ANY,type);
          }
 else {
            for (            String typeFromSOQL : typesFromSOQL) {
              validateCRUDCheckPresent(node,data,ANY,typeFromSOQL);
            }
          }
        }
      }
    }
  }
  final ASTReturnStatement returnStatement=node.getFirstParentOfType(ASTReturnStatement.class);
  if (returnStatement != null) {
    if (!isGetter) {
      if (typesFromSOQL.isEmpty()) {
        validateCRUDCheckPresent(node,data,ANY,returnType);
      }
 else {
        for (        String typeFromSOQL : typesFromSOQL) {
          validateCRUDCheckPresent(node,data,ANY,typeFromSOQL);
        }
      }
    }
  }
}
