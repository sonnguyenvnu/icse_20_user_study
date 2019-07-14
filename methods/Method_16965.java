private Set<List<Object>> combinations(){
  Set<Feature> keyStrengths=ImmutableSet.of(Feature.STRONG_KEYS,Feature.WEAK_KEYS);
  Set<Feature> valueStrengths=ImmutableSet.of(Feature.STRONG_VALUES,Feature.WEAK_VALUES,Feature.SOFT_VALUES);
  Set<Boolean> expireAfterAccess=ImmutableSet.of(false,true);
  Set<Boolean> expireAfterWrite=ImmutableSet.of(false,true);
  Set<Boolean> refreshAfterWrite=ImmutableSet.of(false,true);
  Set<Boolean> maximumSize=ImmutableSet.of(false,true);
  Set<Boolean> weighed=ImmutableSet.of(false,true);
  @SuppressWarnings("unchecked") Set<List<Object>> combinations=Sets.cartesianProduct(keyStrengths,valueStrengths,expireAfterAccess,expireAfterWrite,refreshAfterWrite,maximumSize,weighed);
  return combinations;
}
