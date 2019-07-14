@Override public boolean exists() throws BackendException {
  if (mode != Mode.CLOUD)   throw new UnsupportedOperationException("Operation only supported for SolrCloud");
  final CloudSolrClient server=(CloudSolrClient)solrClient;
  try {
    final ZkStateReader zkStateReader=server.getZkStateReader();
    zkStateReader.forciblyRefreshAllClusterStateSlow();
    final ClusterState clusterState=zkStateReader.getClusterState();
    final Map<String,DocCollection> collections=clusterState.getCollectionsMap();
    return collections != null && !collections.isEmpty();
  }
 catch (  KeeperException|InterruptedException e) {
    throw new PermanentBackendException("Unable to check if index exists",e);
  }
}
