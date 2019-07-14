private boolean isLastMethodName(final ASTMethodCallExpression methodNode,final String className,final String methodName){
  final ASTReferenceExpression reference=methodNode.getFirstChildOfType(ASTReferenceExpression.class);
  if (reference != null && reference.getNode().getNames().size() > 0) {
    if (reference.getNode().getNames().get(reference.getNode().getNames().size() - 1).getValue().equalsIgnoreCase(className) && Helper.isMethodName(methodNode,methodName)) {
      return true;
    }
  }
  return false;
}
