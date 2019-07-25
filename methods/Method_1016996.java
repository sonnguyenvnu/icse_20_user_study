@Override public AggregationInstance apply(final AggregationContext context){
  ListIterator<Aggregation> it=chain.listIterator(chain.size());
  AggregationContext current=context;
  final ImmutableSet.Builder<String> tags=ImmutableSet.builder();
  final ImmutableList.Builder<AggregationInstance> chain=ImmutableList.builder();
  while (it.hasPrevious()) {
    final AggregationInstance instance=it.previous().apply(current);
    tags.addAll(instance.requiredTags());
    current=current.withRequiredTags(tags.build());
    chain.add(instance);
  }
  return ChainInstance.fromList(Lists.reverse(chain.build()));
}
