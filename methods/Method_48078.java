private KsDef ensureKeyspaceExists(String keyspaceName) throws BackendException {
  CTConnection connection=null;
  try {
    connection=pool.borrowObject(SYSTEM_KS);
    Cassandra.Client client=connection.getClient();
    try {
      client.set_keyspace(keyspaceName);
      client.set_keyspace(SYSTEM_KS);
      log.debug("Found existing keyspace {}",keyspaceName);
    }
 catch (    InvalidRequestException e) {
      log.debug("Creating keyspace {}...",keyspaceName);
      final KsDef ksdef=new KsDef().setName(keyspaceName).setCf_defs(new LinkedList<>()).setStrategy_class(storageConfig.get(REPLICATION_STRATEGY)).setStrategy_options(strategyOptions);
      client.set_keyspace(SYSTEM_KS);
      try {
        client.system_add_keyspace(ksdef);
        retrySetKeyspace(keyspaceName,client);
        log.debug("Created keyspace {}",keyspaceName);
      }
 catch (      InvalidRequestException ire) {
        log.error("system_add_keyspace failed for keyspace=" + keyspaceName,ire);
        throw ire;
      }
    }
    return client.describe_keyspace(keyspaceName);
  }
 catch (  Exception e) {
    throw new TemporaryBackendException(e);
  }
 finally {
    pool.returnObjectUnsafe(SYSTEM_KS,connection);
  }
}
