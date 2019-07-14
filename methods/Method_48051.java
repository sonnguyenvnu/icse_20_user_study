private void ensureKeyspaceExists(Cluster cl) throws BackendException {
  KeyspaceDefinition ksDef;
  try {
    ksDef=cl.describeKeyspace(keySpaceName);
    if (null != ksDef && ksDef.getName().equals(keySpaceName)) {
      log.debug("Found keyspace {}",keySpaceName);
      return;
    }
  }
 catch (  ConnectionException e) {
    log.debug("Failed to describe keyspace {}",keySpaceName);
  }
  log.debug("Creating keyspace {}...",keySpaceName);
  try {
    ksDef=cl.makeKeyspaceDefinition().setName(keySpaceName).setStrategyClass(storageConfig.get(REPLICATION_STRATEGY)).setStrategyOptions(strategyOptions);
    cl.addKeyspace(ksDef);
    log.debug("Created keyspace {}",keySpaceName);
  }
 catch (  ConnectionException e) {
    log.debug("Failed to create keyspace {}",keySpaceName);
    throw new TemporaryBackendException(e);
  }
}
