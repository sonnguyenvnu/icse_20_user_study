private void ensureKeyspaceExists(String keyspaceName) throws BackendException {
  if (null != Schema.instance.getKeyspaceInstance(keyspaceName))   return;
  String strategyName=storageConfig.get(REPLICATION_STRATEGY);
  KSMetaData ksm;
  try {
    ksm=KSMetaData.newKeyspace(keyspaceName,strategyName,strategyOptions,true);
  }
 catch (  ConfigurationException e) {
    throw new PermanentBackendException("Failed to instantiate keyspace metadata for " + keyspaceName,e);
  }
  try {
    MigrationManager.announceNewKeyspace(ksm);
    log.info("Created keyspace {}",keyspaceName);
  }
 catch (  ConfigurationException e) {
    throw new PermanentBackendException("Failed to create keyspace " + keyspaceName,e);
  }
}
