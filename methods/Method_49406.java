private String determineKeyspaceName(Configuration config){
  if ((!config.has(KEYSPACE) && (config.has(GRAPH_NAME))))   return config.get(GRAPH_NAME);
  return config.get(KEYSPACE);
}
