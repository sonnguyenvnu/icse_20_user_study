@Override public final GroupInstance apply(final AggregationContext context){
  final AggregationInstance instance=aggregation().flatMap(AggregationOrList::toAggregation).orElse(Empty.INSTANCE).apply(context);
  final Optional<List<String>> of=this.grouping().map(o -> {
    final ImmutableSet.Builder<String> b=ImmutableSet.builder();
    b.addAll(o).addAll(context.requiredTags());
    return ImmutableList.copyOf(b.build());
  }
);
  return new GroupInstance(of,instance);
}
