public AggregationLinkInfo find(@NotNull SContainmentLink link){
  SContainmentLinkId id=MetaIdHelper.getAggregation(link);
  return myRegistry.get(id.getConceptId()).find(id);
}
