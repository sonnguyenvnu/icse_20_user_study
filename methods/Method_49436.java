@Override public Long totals(RawQuery query,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  final int size=query.hasLimit() ? Math.min(query.getLimit() + query.getOffset(),batchSize) : batchSize;
  final ElasticSearchResponse response=runCommonQuery(query,information,tx,size,false);
  log.debug("Executed query [{}] in {} ms",query.getQuery(),response.getTook());
  return response.getTotal();
}
