@Override public PollResult poll(boolean initial,Object checkPoint) throws Exception {
  String table=tableName.get();
  Map<String,Object> map=loadPropertiesFromTable(table);
  log.info("Successfully polled Dynamo for a new configuration based on table:" + table);
  return PollResult.createFull(map);
}
