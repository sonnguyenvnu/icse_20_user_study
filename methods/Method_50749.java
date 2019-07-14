private void extractObjectTypeFromESAPI(final ASTMethodCallExpression node,final String dmlOperation){
  final ASTVariableExpression var=node.getFirstChildOfType(ASTVariableExpression.class);
  if (var != null) {
    final ASTReferenceExpression reference=var.getFirstChildOfType(ASTReferenceExpression.class);
    if (reference != null) {
      List<Identifier> identifiers=reference.getNode().getNames();
      if (identifiers.size() == 1) {
        StringBuilder sb=new StringBuilder().append(node.getNode().getDefiningType().getApexName()).append(":").append(identifiers.get(0).getValue());
        checkedTypeToDMLOperationViaESAPI.put(sb.toString(),dmlOperation);
      }
    }
  }
}
