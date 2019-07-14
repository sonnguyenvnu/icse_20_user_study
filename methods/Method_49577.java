@Override public List<KeyRange> getLocalKeyPartition() throws BackendException {
  List<KeyRange> result=new LinkedList<>();
  try {
    ensureTableExists(tableName,getCfNameForStoreName(GraphDatabaseConfiguration.SYSTEM_PROPERTIES_STORE_NAME),0);
    Map<KeyRange,ServerName> normed=normalizeKeyBounds(cnx.getRegionLocations(tableName));
    for (    Map.Entry<KeyRange,ServerName> e : normed.entrySet()) {
      if (NetworkUtil.isLocalConnection(e.getValue().getHostname())) {
        result.add(e.getKey());
        logger.debug("Found local key/row partition {} on host {}",e.getKey(),e.getValue());
      }
 else {
        logger.debug("Discarding remote {}",e.getValue());
      }
    }
  }
 catch (  MasterNotRunningException e) {
    logger.warn("Unexpected MasterNotRunningException",e);
  }
catch (  ZooKeeperConnectionException e) {
    logger.warn("Unexpected ZooKeeperConnectionException",e);
  }
catch (  IOException e) {
    logger.warn("Unexpected IOException",e);
  }
  return result;
}
