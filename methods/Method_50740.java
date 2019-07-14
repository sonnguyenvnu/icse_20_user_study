private String getType(final ASTMethodCallExpression methodNode){
  final ASTReferenceExpression reference=methodNode.getFirstChildOfType(ASTReferenceExpression.class);
  if (reference.getNode().getNames().size() > 0) {
    return new StringBuilder().append(reference.getNode().getDefiningType().getApexName()).append(":").append(reference.getNode().getNames().get(0).getValue()).toString();
  }
  return "";
}
