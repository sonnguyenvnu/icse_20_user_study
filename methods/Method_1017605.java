@Override public Query wrap(List<NativeQuery> queries){
  if (queries.isEmpty()) {
    return emptyQuery;
  }
  if (queries.size() == 1) {
    NativeQuery firstQuery=queries.get(0);
    if (isReWriteBatchedInsertsEnabled() && firstQuery.getCommand().isBatchedReWriteCompatible()) {
      int valuesBraceOpenPosition=firstQuery.getCommand().getBatchRewriteValuesBraceOpenPosition();
      int valuesBraceClosePosition=firstQuery.getCommand().getBatchRewriteValuesBraceClosePosition();
      return new BatchedQuery(firstQuery,this,valuesBraceOpenPosition,valuesBraceClosePosition,isColumnSanitiserDisabled());
    }
 else {
      return new SimpleQuery(firstQuery,this,isColumnSanitiserDisabled());
    }
  }
  SimpleQuery[] subqueries=new SimpleQuery[queries.size()];
  int[] offsets=new int[subqueries.length];
  int offset=0;
  for (int i=0; i < queries.size(); ++i) {
    NativeQuery nativeQuery=queries.get(i);
    offsets[i]=offset;
    subqueries[i]=new SimpleQuery(nativeQuery,this,isColumnSanitiserDisabled());
    offset+=nativeQuery.bindPositions.length;
  }
  return new CompositeQuery(subqueries,offsets);
}
