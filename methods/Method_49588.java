private String determineTableName(org.janusgraph.diskstorage.configuration.Configuration config){
  if ((!config.has(HBASE_TABLE)) && (config.has(GRAPH_NAME))) {
    return config.get(GRAPH_NAME);
  }
  return config.get(HBASE_TABLE);
}
