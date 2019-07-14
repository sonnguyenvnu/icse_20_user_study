private SolrQuery runCommonQuery(RawQuery query,KeyInformation.IndexRetriever information,BaseTransaction tx,String collection,String keyIdField) throws BackendException {
  final SolrQuery solrQuery=new SolrQuery(query.getQuery()).addField(keyIdField).setIncludeScore(true).setStart(query.getOffset());
  if (query.hasLimit()) {
    solrQuery.setRows(Math.min(query.getLimit(),batchSize));
  }
 else {
    solrQuery.setRows(batchSize);
  }
  if (!query.getOrders().isEmpty()) {
    addOrderToQuery(solrQuery,query.getOrders());
  }
  for (  final Parameter parameter : query.getParameters()) {
    if (parameter.value() instanceof String[]) {
      solrQuery.setParam(parameter.key(),(String[])parameter.value());
    }
 else     if (parameter.value() instanceof String) {
      solrQuery.setParam(parameter.key(),(String)parameter.value());
    }
  }
  return solrQuery;
}
