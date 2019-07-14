private void retrySetKeyspace(String ksName,Cassandra.Client client) throws BackendException {
  final long end=System.currentTimeMillis() + (60L * 1000L);
  while (System.currentTimeMillis() <= end) {
    try {
      client.set_keyspace(ksName);
      return;
    }
 catch (    Exception e) {
      log.warn("Exception when changing to keyspace {} after creating it",ksName,e);
      try {
        Thread.sleep(1000L);
      }
 catch (      InterruptedException ie) {
        throw new PermanentBackendException("Unexpected interrupt (shutting down?)",ie);
      }
    }
  }
  throw new PermanentBackendException("Could change to keyspace " + ksName + " after creating it");
}
