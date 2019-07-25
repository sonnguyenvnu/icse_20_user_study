protected synchronized void insert(Connection connection,PingData data,String clustername,String address) throws SQLException {
  final byte[] serializedPingData=serializeWithoutView(data);
  try (PreparedStatement ps=connection.prepareStatement(insert_single_sql)){
    ps.setString(1,address);
    ps.setString(2,clustername);
    ps.setBytes(3,serializedPingData);
    if (log.isTraceEnabled())     log.trace("%s: SQL for insertion: %s",local_addr,ps);
    ps.executeUpdate();
    log.debug("Registered %s for clustername %s into database",address,clustername);
  }
 }
