public Long totals(final String index,final RawQuery query){
  final IndexTransaction indexTx=getIndexTransaction(index);
  return executeRead(new TotalsCallable(query,indexTx));
}
