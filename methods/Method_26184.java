private Description matchDereference(ExpressionTree dereferencedExpression,VisitorState state){
  Symbol dereferenced=ASTHelpers.getSymbol(dereferencedExpression);
  if (dereferenced == null || dereferenced.getKind() != ElementKind.PARAMETER || dereferenced.type.isPrimitive()) {
    return Description.NO_MATCH;
  }
  if (!TrustingNullnessAnalysis.hasNullableAnnotation(dereferenced)) {
    return Description.NO_MATCH;
  }
  Nullness nullness=TrustingNullnessAnalysis.instance(state.context).getNullness(new TreePath(state.getPath(),dereferencedExpression),state.context);
  if (nullness != Nullness.NULLABLE) {
    return Description.NO_MATCH;
  }
  VariableTree paramDecl=findDeclaration(state,dereferenced);
  for (  AnnotationTree anno : paramDecl.getModifiers().getAnnotations()) {
    String annoType=ASTHelpers.getSymbol(anno).type.toString();
    if (annoType.endsWith(".Nullable") || annoType.endsWith(".NullableDecl") || annoType.endsWith(".CheckForNull") || annoType.endsWith(".RecentlyNullable")) {
      return buildDescription(dereferencedExpression).setMessage("Nullable parameter not checked for null").addFix(SuggestedFix.delete(anno)).build();
    }
  }
  if (paramDecl.getType() instanceof AnnotatedTypeTree) {
    for (    AnnotationTree anno : ((AnnotatedTypeTree)paramDecl.getType()).getAnnotations()) {
      if (ASTHelpers.getSymbol(anno).type.toString().endsWith(".Nullable")) {
        return buildDescription(dereferencedExpression).setMessage("Nullable parameter not checked for null").addFix(SuggestedFix.delete(anno)).build();
      }
    }
  }
  return Description.NO_MATCH;
}
