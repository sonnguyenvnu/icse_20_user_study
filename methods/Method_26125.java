private List<Fix> buildFixes(MethodInvocationTree methodInvocationTree,VisitorState state,ExpressionTree receiver,ExpressionTree argument){
  List<Fix> fixes;
  if (receiver.getKind() == MEMBER_SELECT) {
    fixes=fixesByReplacingExpressionWithMethodParameter(argument,isCollectionVariable(state),state);
  }
 else {
    Preconditions.checkState(receiver.getKind() == IDENTIFIER,"receiver.getKind is identifier");
    boolean lhsIsField=ASTHelpers.getSymbol(receiver).getKind() == ElementKind.FIELD;
    fixes=lhsIsField ? fixesByReplacingExpressionWithMethodParameter(argument,isCollectionVariable(state),state) : fixesByReplacingExpressionWithLocallyDeclaredField(receiver,isCollectionVariable(state),state);
  }
  if (fixes.isEmpty()) {
    fixes=literalReplacement(methodInvocationTree,state,receiver);
  }
  return fixes;
}
