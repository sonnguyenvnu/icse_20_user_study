@Override public AggregationInstance distributed(){
  final Iterator<AggregationInstance> it=chain.iterator();
  final ImmutableList.Builder<AggregationInstance> chain=ImmutableList.builder();
  AggregationInstance last=it.next();
  if (!last.distributable()) {
    return EmptyInstance.INSTANCE;
  }
  while (it.hasNext()) {
    final AggregationInstance next=it.next();
    if (!next.distributable()) {
      chain.add(last.distributed());
      return fromList(chain.build());
    }
    chain.add(last);
    last=next;
  }
  chain.add(last.distributed());
  return fromList(chain.build());
}
