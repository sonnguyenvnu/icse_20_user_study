@Override public Description matchAssignment(AssignmentTree tree,VisitorState state){
  Symbol assigned=ASTHelpers.getSymbol(tree.getVariable());
  if (assigned == null || assigned.getKind() != ElementKind.FIELD || assigned.type.isPrimitive()) {
    return Description.NO_MATCH;
  }
  ExpressionTree expression=tree.getExpression();
  if (ASTHelpers.constValue(expression) != null) {
    return Description.NO_MATCH;
  }
  if (TrustingNullnessAnalysis.hasNullableAnnotation(assigned)) {
    return Description.NO_MATCH;
  }
  VariableTree fieldDecl=findDeclaration(state,assigned);
  if (fieldDecl == null) {
    return Description.NO_MATCH;
  }
  if (expression.getKind() == Tree.Kind.NULL_LITERAL) {
    return makeFix(state,fieldDecl,tree,"Assigning null literal to field");
  }
  Nullness nullness=TrustingNullnessAnalysis.instance(state.context).getNullness(new TreePath(state.getPath(),expression),state.context);
  if (nullness == null) {
    return Description.NO_MATCH;
  }
switch (nullness) {
case BOTTOM:
case NONNULL:
    return Description.NO_MATCH;
case NULL:
  return makeFix(state,fieldDecl,tree,"Assigning null to field");
case NULLABLE:
return makeFix(state,fieldDecl,tree,"May assign null to field");
default :
throw new AssertionError("Impossible: " + nullness);
}
}
