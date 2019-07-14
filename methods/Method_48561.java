private ImmutableList<IndexQuery.OrderEntry> getOrders(IndexQueryBuilder query,final ElementCategory resultType,final StandardJanusGraphTx transaction,MixedIndexType index){
  if (query.getOrders() == null) {
    return ImmutableList.of();
  }
  Preconditions.checkArgument(index.getElement() == resultType,"Index is not configured for the desired result type: %s",resultType);
  List<IndexQuery.OrderEntry> orderReplacement=new ArrayList<>();
  for (  Parameter<Order> order : query.getOrders()) {
    if (transaction.containsRelationType(order.key())) {
      final PropertyKey key=transaction.getPropertyKey(order.key());
      Preconditions.checkNotNull(key);
      Preconditions.checkArgument(index.indexesKey(key),"The used key [%s] is not indexed in the targeted index [%s]",key.name(),query.getIndex());
      orderReplacement.add(new IndexQuery.OrderEntry(key2Field(index,key),org.janusgraph.graphdb.internal.Order.convert(order.value()),key.dataType()));
    }
 else {
      Preconditions.checkArgument(query.getUnknownKeyName() != null,"Found reference to non-existant property key in query orders %s",order.key());
    }
  }
  return ImmutableList.copyOf(orderReplacement);
}
