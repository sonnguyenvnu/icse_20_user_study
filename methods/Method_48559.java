public IndexQuery getQuery(final MixedIndexType index,final Condition condition,final OrderList orders){
  final Condition newCondition=ConditionUtil.literalTransformation(condition,new Function<Condition<JanusGraphElement>,Condition<JanusGraphElement>>(){
    @Nullable @Override public Condition<JanusGraphElement> apply(    final Condition<JanusGraphElement> condition){
      Preconditions.checkArgument(condition instanceof PredicateCondition);
      final PredicateCondition pc=(PredicateCondition)condition;
      final PropertyKey key=(PropertyKey)pc.getKey();
      return new PredicateCondition<>(key2Field(index,key),pc.getPredicate(),pc.getValue());
    }
  }
);
  ImmutableList<IndexQuery.OrderEntry> newOrders=IndexQuery.NO_ORDER;
  if (!orders.isEmpty() && GraphCentricQueryBuilder.indexCoversOrder(index,orders)) {
    final ImmutableList.Builder<IndexQuery.OrderEntry> lb=ImmutableList.builder();
    for (int i=0; i < orders.size(); i++) {
      lb.add(new IndexQuery.OrderEntry(key2Field(index,orders.getKey(i)),orders.getOrder(i),orders.getKey(i).dataType()));
    }
    newOrders=lb.build();
  }
  return new IndexQuery(index.getStoreName(),newCondition,newOrders);
}
