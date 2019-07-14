public ModifiableConfiguration getJanusGraphConf(){
  return prefixView(GraphDatabaseConfiguration.ROOT_NS,JanusGraphHadoopConfiguration.GRAPH_CONFIG_KEYS,this);
}
