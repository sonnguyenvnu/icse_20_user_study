public Long executeTotals(IndexQueryBuilder query,final ElementCategory resultType,final BackendTransaction backendTx,final StandardJanusGraphTx transaction){
  final MixedIndexType index=getMixedIndex(query.getIndex(),transaction);
  final String queryStr=createQueryString(query,resultType,transaction,index);
  final RawQuery rawQuery=new RawQuery(index.getStoreName(),queryStr,query.getParameters());
  if (query.hasLimit())   rawQuery.setLimit(query.getLimit());
  rawQuery.setOffset(query.getOffset());
  return backendTx.totals(index.getBackingIndexName(),rawQuery);
}
