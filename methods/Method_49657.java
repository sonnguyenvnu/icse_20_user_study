private static void createCollectionIfNotExists(CloudSolrClient client,Configuration config,String collection) throws IOException, SolrServerException, KeeperException, InterruptedException {
  if (!checkIfCollectionExists(client,collection)) {
    final Integer numShards=config.get(NUM_SHARDS);
    final Integer maxShardsPerNode=config.get(MAX_SHARDS_PER_NODE);
    final Integer replicationFactor=config.get(REPLICATION_FACTOR);
    final String genericConfigSet=config.has(SOLR_DEFAULT_CONFIG) ? config.get(SOLR_DEFAULT_CONFIG) : collection;
    final CollectionAdminRequest.Create createRequest=CollectionAdminRequest.createCollection(collection,genericConfigSet,numShards,replicationFactor);
    createRequest.setMaxShardsPerNode(maxShardsPerNode);
    final CollectionAdminResponse createResponse=createRequest.process(client);
    if (createResponse.isSuccess()) {
      logger.trace("Collection {} successfully created.",collection);
    }
 else {
      throw new SolrServerException(Joiner.on("\n").join(createResponse.getErrorMessages()));
    }
  }
  waitForRecoveriesToFinish(client,collection);
}
