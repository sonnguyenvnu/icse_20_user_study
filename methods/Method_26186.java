@Override public Description matchReturn(ReturnTree tree,VisitorState state){
  ExpressionTree returnExpression=tree.getExpression();
  if (returnExpression == null) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.constValue(returnExpression) != null) {
    return Description.NO_MATCH;
  }
  JCMethodDecl method=findSurroundingMethod(state.getPath());
  if (method == null || isIgnoredReturnType(method,state)) {
    return Description.NO_MATCH;
  }
  if (TrustingNullnessAnalysis.hasNullableAnnotation(method.sym)) {
    return Description.NO_MATCH;
  }
  if (returnExpression.getKind() == ExpressionTree.Kind.NULL_LITERAL) {
    return makeFix(state,method,tree,"Returning null literal");
  }
  Nullness nullness=TrustingNullnessAnalysis.instance(state.context).getNullness(new TreePath(state.getPath(),returnExpression),state.context);
switch (nullness) {
case BOTTOM:
case NONNULL:
    return Description.NO_MATCH;
case NULL:
  return makeFix(state,method,tree,"Definitely returning null");
case NULLABLE:
return makeFix(state,method,tree,"May return null");
default :
throw new AssertionError("Impossible: " + nullness);
}
}
