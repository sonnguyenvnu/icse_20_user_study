/** 
 * Checks if the collection has already been created in Solr.
 */
private static boolean checkIfCollectionExists(CloudSolrClient server,String collection) throws KeeperException, InterruptedException {
  final ZkStateReader zkStateReader=server.getZkStateReader();
  zkStateReader.forceUpdateCollection(collection);
  final ClusterState clusterState=zkStateReader.getClusterState();
  return clusterState.getCollectionOrNull(collection) != null;
}
