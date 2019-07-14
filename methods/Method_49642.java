@Override public Long totals(RawQuery query,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  try {
    final String collection=query.getStore();
    final String keyIdField=getKeyFieldId(collection);
    final QueryResponse response=solrClient.query(collection,runCommonQuery(query,information,tx,collection,keyIdField));
    logger.debug("Executed query [{}] in {} ms",query.getQuery(),response.getElapsedTime());
    return response.getResults().getNumFound();
  }
 catch (  final IOException e) {
    logger.error("Query did not complete : ",e);
    throw new PermanentBackendException(e);
  }
catch (  final SolrServerException e) {
    logger.error("Unable to query Solr index.",e);
    throw new PermanentBackendException(e);
  }
}
