AggregationLinkInfo find(@NotNull SContainmentLinkId id){
  assert myAggregations.containsKey(id);
  return myAggregations.get(id);
}
