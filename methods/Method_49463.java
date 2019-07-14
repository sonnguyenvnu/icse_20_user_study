/** 
 * Opens the graph instance. If the graph instance does not exist, a new graph instance is initialized.
 */
public GraphTraversalSource openGraph() throws ConfigurationException {
  LOGGER.info("opening graph");
  conf=new PropertiesConfiguration(propFileName);
  graph=GraphFactory.open(conf);
  g=graph.traversal();
  return g;
}
