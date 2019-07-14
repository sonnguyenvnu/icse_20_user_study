private static ImmutableList<ParameterOrderingViolation> getViolations(List<MethodTree> groupMethodTrees){
  ImmutableList.Builder<ParameterOrderingViolation> result=ImmutableList.builder();
  ParameterTrie trie=new ParameterTrie();
  for (  MethodTree methodTree : sortedByArity(groupMethodTrees)) {
    Optional<ParameterOrderingViolation> violation=trie.extendAndComputeViolation(methodTree);
    violation.ifPresent(result::add);
  }
  return result.build();
}
