/** 
 * Update configuration corresponding to supplied graphName; we update supplied existing properties and add new ones to the  {@link Configuration}; The supplied  {@link Configuration} must include aproperty "graph.graphname" and it must match supplied graphName; NOTE: The updated configuration is only guaranteed to take effect if the  {@link Graph} corresponding tographName has been closed and reopened on every JanusGraph Node.
 */
public void updateConfiguration(final String graphName,final Configuration config){
  final Map<Object,Object> map=ConfigurationConverter.getMap(config);
  if (config.containsKey(PROPERTY_GRAPH_NAME)) {
    final String graphNameOnConfig=(String)map.get(PROPERTY_GRAPH_NAME);
    Preconditions.checkArgument(graphName.equals(graphNameOnConfig),String.format("Supplied graphName %s does not match property value supplied on config: %s.",graphName,graphNameOnConfig));
  }
 else {
    map.put(PROPERTY_GRAPH_NAME,graphName);
  }
  log.warn("Configuration {} is only guaranteed to take effect when graph {} has been closed and reopened on all Janus Graph Nodes.",graphName,graphName);
  updateVertexWithProperties(PROPERTY_GRAPH_NAME,graphName,map);
}
