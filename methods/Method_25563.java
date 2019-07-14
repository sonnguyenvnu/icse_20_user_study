private static ImmutableList<Commented<ExpressionTree>> noComments(List<? extends ExpressionTree> arguments){
  return arguments.stream().map(a -> Commented.<ExpressionTree>builder().setTree(a).build()).collect(toImmutableList());
}
