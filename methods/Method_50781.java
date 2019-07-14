@Override public Object visit(ASTUserClass node,Object data){
  if (Helper.isTestMethodOrClass(node) || Helper.isSystemLevelClass(node)) {
    return data;
  }
  List<ASTMethodCallExpression> methodCalls=node.findDescendantsOfType(ASTMethodCallExpression.class);
  for (  ASTMethodCallExpression methodCall : methodCalls) {
    if (Helper.isMethodName(methodCall,ADD_ERROR)) {
      validateBooleanParameter(methodCall,data);
    }
  }
  return data;
}
