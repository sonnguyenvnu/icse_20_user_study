@Override public boolean exists() throws BackendException {
  CTConnection connection=null;
  try {
    connection=pool.borrowObject(SYSTEM_KS);
    final Cassandra.Client client=connection.getClient();
    try {
      client.set_keyspace(keySpaceName);
      client.set_keyspace(SYSTEM_KS);
      return true;
    }
 catch (    InvalidRequestException e) {
      return false;
    }
  }
 catch (  Exception e) {
    throw new TemporaryBackendException(e);
  }
 finally {
    pool.returnObjectUnsafe(SYSTEM_KS,connection);
  }
}
