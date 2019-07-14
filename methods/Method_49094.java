private <Q extends BaseVertexQuery>Q makeQuery(Q query){
  final String[] keys=getPropertyKeys();
  query.keys(keys);
  for (  final HasContainer condition : hasContainers) {
    query.has(condition.getKey(),JanusGraphPredicate.Converter.convert(condition.getBiPredicate()),condition.getValue());
  }
  for (  final OrderEntry order : orders)   query.orderBy(order.key,order.order);
  if (limit != BaseQuery.NO_LIMIT)   query.limit(limit);
  ((BasicVertexCentricQueryBuilder)query).profiler(queryProfiler);
  return query;
}
