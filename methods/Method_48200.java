private void setupUpgradeConfiguration(String graphName,ModifiableConfiguration globalWrite){
  if (!globalWrite.has(INITIAL_STORAGE_VERSION)) {
    janusGraphVersionsWithDisallowedUpgrade(globalWrite);
    log.info("graph.storage-version has been upgraded from 1 to {} and graph.janusgraph-version has been upgraded from {} to {} on graph {}",JanusGraphConstants.STORAGE_VERSION,globalWrite.get(INITIAL_JANUSGRAPH_VERSION),JanusGraphConstants.VERSION,graphName);
    return;
  }
  int storageVersion=Integer.parseInt(JanusGraphConstants.STORAGE_VERSION);
  int initialStorageVersion=Integer.parseInt(globalWrite.get(INITIAL_STORAGE_VERSION));
  if (initialStorageVersion > storageVersion) {
    throw new JanusGraphException(String.format(BACKLEVEL_STORAGE_VERSION_EXCEPTION,globalWrite.get(INITIAL_STORAGE_VERSION),JanusGraphConstants.STORAGE_VERSION,graphName));
  }
  if (initialStorageVersion < storageVersion) {
    janusGraphVersionsWithDisallowedUpgrade(globalWrite);
    log.info("graph.storage-version has been upgraded from {} to {} and graph.janusgraph-version has been upgraded from {} to {} on graph {}",globalWrite.get(INITIAL_STORAGE_VERSION),JanusGraphConstants.STORAGE_VERSION,globalWrite.get(INITIAL_JANUSGRAPH_VERSION),JanusGraphConstants.VERSION,graphName);
  }
 else {
    log.warn("Warning graph.allow-upgrade is currently set to true on graph {}. Please set graph.allow-upgrade to false in your properties file.",graphName);
  }
}
