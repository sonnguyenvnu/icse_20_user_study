private Set<List<Object>> combinations(){
  Set<Boolean> options=ImmutableSet.of(true,false);
  List<Set<Boolean>> sets=Collections.nCopies(featureByIndex.length,options);
  return Sets.cartesianProduct(sets);
}
