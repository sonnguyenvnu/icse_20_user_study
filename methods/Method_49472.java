@Override public GraphTraversalSource openGraph() throws ConfigurationException {
  LOGGER.info("opening graph");
  conf=new PropertiesConfiguration(propFileName);
  try {
    cluster=Cluster.open(conf.getString("gremlin.remote.driver.clusterFile"));
    client=cluster.connect();
  }
 catch (  Exception e) {
    throw new ConfigurationException(e);
  }
  graph=EmptyGraph.instance();
  g=graph.traversal().withRemote(conf);
  return g;
}
