static ImmutableList<Parameter> createListFromExpressionTrees(List<? extends ExpressionTree> trees){
  return Streams.mapWithIndex(trees.stream(),(t,i) -> new AutoValue_Parameter(getArgumentName(t),Optional.ofNullable(ASTHelpers.getResultType(t)).orElse(Type.noType),(int)i,t.toString(),t.getKind(),ASTHelpers.constValue(t) != null)).collect(toImmutableList());
}
