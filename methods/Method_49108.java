private GraphCentricQuery buildGlobalGraphCentricQuery(final JanusGraphTransaction tx){
  final Iterator<QueryInfo> itQueryInfo=hasLocalContainers.values().iterator();
  QueryInfo queryInfo=itQueryInfo.next();
  if (queryInfo.getLowLimit() > 0 || orders.isEmpty() && !queryInfo.getOrders().isEmpty()) {
    return null;
  }
  final Integer limit=queryInfo.getHighLimit();
  while (itQueryInfo.hasNext()) {
    queryInfo=itQueryInfo.next();
    if (queryInfo.getLowLimit() > 0 || (orders.isEmpty() && !queryInfo.getOrders().isEmpty()) || (queryInfo.getHighLimit() < highLimit && !limit.equals(queryInfo.getHighLimit()))) {
      return null;
    }
  }
  final JanusGraphQuery query=tx.query();
  for (  final List<HasContainer> localContainers : hasLocalContainers.keySet()) {
    final JanusGraphQuery localQuery=tx.query();
    addConstraint(localQuery,localContainers);
    query.or(localQuery);
  }
  for (  final OrderEntry order : orders)   query.orderBy(order.key,order.order);
  if (highLimit != BaseQuery.NO_LIMIT || limit != BaseQuery.NO_LIMIT)   query.limit(Math.min(limit,highLimit));
  Preconditions.checkArgument(query instanceof GraphCentricQueryBuilder);
  final GraphCentricQueryBuilder centricQueryBuilder=((GraphCentricQueryBuilder)query);
  centricQueryBuilder.profiler(queryProfiler);
  final GraphCentricQuery graphCentricQuery=centricQueryBuilder.constructQuery(Vertex.class.isAssignableFrom(this.returnClass) ? ElementCategory.VERTEX : ElementCategory.EDGE);
  return graphCentricQuery;
}
