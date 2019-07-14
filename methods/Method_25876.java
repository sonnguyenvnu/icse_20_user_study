@Override protected Description handleMatch(MethodTree tree,VisitorState state,List<Tree> expectations,List<StatementTree> suffix,@Nullable StatementTree failure){
  if (suffix.size() <= 1) {
    return NO_MATCH;
  }
  BaseFix baseFix=buildBaseFix(state,expectations,failure);
  ImmutableList<Fix> fixes=Lists.reverse(suffix).stream().filter(t -> !JUnitMatchers.containsTestMethod(t)).map(t -> baseFix.build(ImmutableList.of(t))).collect(toImmutableList());
  if (fixes.isEmpty()) {
    fixes=ImmutableList.of(baseFix.build(ImmutableList.of(getLast(suffix))));
  }
  return buildDescription(tree).addAllFixes(fixes).build();
}
