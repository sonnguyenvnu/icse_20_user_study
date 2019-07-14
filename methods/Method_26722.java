@Nullable private List<Tree> templateTrees(@Nullable Iterable<? extends Tree> trees){
  if (trees == null) {
    return null;
  }
  ImmutableList.Builder<Tree> builder=ImmutableList.builder();
  for (  Tree tree : trees) {
    builder.add(template(tree));
  }
  return builder.build();
}
