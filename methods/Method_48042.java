protected String determineKeyspaceName(Configuration config){
  if ((!config.has(CASSANDRA_KEYSPACE) && (config.has(GRAPH_NAME))))   return config.get(GRAPH_NAME);
  return config.get(CASSANDRA_KEYSPACE);
}
