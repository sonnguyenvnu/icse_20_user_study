@Override public CTConnection makeObject(String key) throws Exception {
  CTConnection conn=makeRawConnection();
  Cassandra.Client client=conn.getClient();
  client.set_keyspace(key);
  return conn;
}
