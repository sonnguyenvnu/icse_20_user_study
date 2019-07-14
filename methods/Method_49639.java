private void commitChanges(String collectionName,Collection<SolrInputDocument> documents) throws SolrServerException, IOException {
  if (documents.size() == 0)   return;
  try {
    solrClient.request(newUpdateRequest().add(documents),collectionName);
  }
 catch (  final HttpSolrClient.RemoteSolrException rse) {
    logger.error("Unable to save documents to Solr as one of the shape objects stored were not compatible with Solr.",rse);
    logger.error("Details in failed document batch: ");
    for (    final SolrInputDocument d : documents) {
      final Collection<String> fieldNames=d.getFieldNames();
      for (      final String name : fieldNames) {
        logger.error(name + ":" + d.getFieldValue(name));
      }
    }
    throw rse;
  }
}
