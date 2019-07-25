public HeartbeatRowMap recover() throws Exception {
  String recoveryMsg=String.format("old-server-id: %d, position: %s",recoveryInfo.serverID,recoveryInfo.position);
  LOGGER.warn("attempting to recover from master-change: " + recoveryMsg);
  List<BinlogPosition> list=getBinlogInfo();
  for (int i=list.size() - 1; i >= 0; i--) {
    BinlogPosition binlogPosition=list.get(i);
    Position position=Position.valueOf(binlogPosition,recoveryInfo.getHeartbeat());
    Metrics metrics=new NoOpMetrics();
    LOGGER.debug("scanning binlog: " + binlogPosition);
    Replicator replicator=new BinlogConnectorReplicator(this.schemaStore,null,null,replicationConfig,0L,maxwellDatabaseName,metrics,position,true,recoveryInfo.clientID,new HeartbeatNotifier(),null,new RecoveryFilter(this.maxwellDatabaseName),new MaxwellOutputConfig());
    HeartbeatRowMap h=findHeartbeat(replicator);
    if (h != null) {
      LOGGER.warn("recovered new master position: " + h.getNextPosition());
      return h;
    }
  }
  LOGGER.error("Could not recover from master-change: " + recoveryMsg);
  return null;
}
