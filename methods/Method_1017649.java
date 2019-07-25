@Override public ReplicationSlotInfo make() throws SQLException {
  if (slotName == null || slotName.isEmpty()) {
    throw new IllegalArgumentException("Replication slotName can't be null");
  }
  Statement statement=connection.createStatement();
  ResultSet result=null;
  ReplicationSlotInfo slotInfo=null;
  try {
    statement.execute(String.format("CREATE_REPLICATION_SLOT %s %s PHYSICAL",slotName,temporaryOption ? "TEMPORARY" : ""));
    result=statement.getResultSet();
    if (result != null && result.next()) {
      slotInfo=new ReplicationSlotInfo(result.getString("slot_name"),ReplicationType.PHYSICAL,LogSequenceNumber.valueOf(result.getString("consistent_point")),result.getString("snapshot_name"),result.getString("output_plugin"));
    }
  }
  finally {
    if (result != null) {
      result.close();
    }
    statement.close();
  }
  return slotInfo;
}
