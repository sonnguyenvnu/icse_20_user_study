@Override public Stream<String> query(IndexQuery query,KeyInformation.IndexRetriever informations,BaseTransaction tx) throws BackendException {
  final ElasticSearchRequest sr=new ElasticSearchRequest();
  final Map<String,Object> esQuery=getFilter(query.getCondition(),informations.get(query.getStore()));
  sr.setQuery(compat.prepareQuery(esQuery));
  if (!query.getOrder().isEmpty()) {
    addOrderToQuery(informations,sr,query.getOrder(),query.getStore());
  }
  sr.setFrom(0);
  if (query.hasLimit()) {
    sr.setSize(Math.min(query.getLimit(),batchSize));
  }
 else {
    sr.setSize(batchSize);
  }
  ElasticSearchResponse response;
  try {
    final String indexStoreName=getIndexStoreName(query.getStore());
    final String indexType=useMultitypeIndex ? query.getStore() : null;
    response=client.search(indexStoreName,indexType,compat.createRequestBody(sr,NULL_PARAMETERS),sr.getSize() >= batchSize);
    log.debug("First Executed query [{}] in {} ms",query.getCondition(),response.getTook());
    final ElasticSearchScroll resultIterator=new ElasticSearchScroll(client,response,sr.getSize());
    final Stream<RawQuery.Result<String>> toReturn=StreamSupport.stream(Spliterators.spliteratorUnknownSize(resultIterator,Spliterator.ORDERED),false);
    return (query.hasLimit() ? toReturn.limit(query.getLimit()) : toReturn).map(RawQuery.Result::getResult);
  }
 catch (  final IOException|UncheckedIOException e) {
    throw new PermanentBackendException(e);
  }
}
