public Stream<RawQuery.Result> executeQuery(IndexQueryBuilder query,final ElementCategory resultType,final BackendTransaction backendTx,final StandardJanusGraphTx transaction){
  final MixedIndexType index=getMixedIndex(query.getIndex(),transaction);
  final String queryStr=createQueryString(query,resultType,transaction,index);
  ImmutableList<IndexQuery.OrderEntry> orders=getOrders(query,resultType,transaction,index);
  final RawQuery rawQuery=new RawQuery(index.getStoreName(),queryStr,orders,query.getParameters());
  if (query.hasLimit())   rawQuery.setLimit(query.getLimit());
  rawQuery.setOffset(query.getOffset());
  return backendTx.rawQuery(index.getBackingIndexName(),rawQuery).map(result -> new RawQuery.Result(string2ElementId(result.getResult()),result.getScore()));
}
