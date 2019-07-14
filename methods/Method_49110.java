private GraphCentricQuery buildGraphCentricQuery(final JanusGraphTransaction tx,final Entry<List<HasContainer>,QueryInfo> containers){
  final JanusGraphQuery query=tx.query();
  addConstraint(query,containers.getKey());
  final List<OrderEntry> realOrders=orders.isEmpty() ? containers.getValue().getOrders() : orders;
  for (  final OrderEntry order : realOrders)   query.orderBy(order.key,order.order);
  if (highLimit != BaseQuery.NO_LIMIT || containers.getValue().getHighLimit() != BaseQuery.NO_LIMIT)   query.limit(Math.min(containers.getValue().getHighLimit(),highLimit));
  Preconditions.checkArgument(query instanceof GraphCentricQueryBuilder);
  final GraphCentricQueryBuilder centricQueryBuilder=((GraphCentricQueryBuilder)query);
  centricQueryBuilder.profiler(queryProfiler);
  final GraphCentricQuery graphCentricQuery=centricQueryBuilder.constructQuery(Vertex.class.isAssignableFrom(this.returnClass) ? ElementCategory.VERTEX : ElementCategory.EDGE);
  return graphCentricQuery;
}
