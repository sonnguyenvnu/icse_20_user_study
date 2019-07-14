@Override public Description matchVariable(VariableTree tree,VisitorState state){
  Symbol assigned=ASTHelpers.getSymbol(tree);
  if (assigned == null || assigned.getKind() != ElementKind.FIELD || assigned.type.isPrimitive()) {
    return Description.NO_MATCH;
  }
  ExpressionTree expression=tree.getInitializer();
  if (expression == null || ASTHelpers.constValue(expression) != null) {
    return Description.NO_MATCH;
  }
  if (TrustingNullnessAnalysis.hasNullableAnnotation(assigned)) {
    return Description.NO_MATCH;
  }
  if (expression.getKind() == Tree.Kind.NULL_LITERAL) {
    return makeFix(state,tree,tree,"Initializing field with null literal");
  }
  Nullness nullness=TrustingNullnessAnalysis.instance(state.context).getFieldInitializerNullness(state.getPath(),state.context);
switch (nullness) {
case BOTTOM:
case NONNULL:
    return Description.NO_MATCH;
case NULL:
  return makeFix(state,tree,tree,"Initializing field with null");
case NULLABLE:
return makeFix(state,tree,tree,"May initialize field with null");
default :
throw new AssertionError("Impossible: " + nullness);
}
}
