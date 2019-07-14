static boolean isMethodName(final ASTMethodCallExpression methodNode,final String className,final String methodName){
  final ASTReferenceExpression reference=methodNode.getFirstChildOfType(ASTReferenceExpression.class);
  return reference != null && reference.getNode().getNames().size() == 1 && reference.getNode().getNames().get(0).getValue().equalsIgnoreCase(className) && (methodName.equals(ANY_METHOD) || isMethodName(methodNode,methodName));
}
