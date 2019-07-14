@Override public void clearStorage() throws BackendException {
  try {
    if (mode != Mode.CLOUD) {
      logger.error("Operation only supported for SolrCloud. Cores must be deleted manually through the Solr API when using HTTP mode.");
      return;
    }
    logger.debug("Clearing storage from Solr: {}",solrClient);
    final ZkStateReader zkStateReader=((CloudSolrClient)solrClient).getZkStateReader();
    zkStateReader.forciblyRefreshAllClusterStateSlow();
    final ClusterState clusterState=zkStateReader.getClusterState();
    for (    final String collection : clusterState.getCollectionsMap().keySet()) {
      logger.debug("Clearing collection [{}] in Solr",collection);
      final UpdateRequest deleteAll=newUpdateRequest();
      deleteAll.deleteByQuery("*:*");
      solrClient.request(deleteAll,collection);
    }
  }
 catch (  final SolrServerException e) {
    logger.error("Unable to clear storage from index due to server error on Solr.",e);
    throw new PermanentBackendException(e);
  }
catch (  final IOException e) {
    logger.error("Unable to clear storage from index due to low-level I/O error.",e);
    throw new PermanentBackendException(e);
  }
catch (  final Exception e) {
    logger.error("Unable to clear storage from index due to general error.",e);
    throw new PermanentBackendException(e);
  }
}
