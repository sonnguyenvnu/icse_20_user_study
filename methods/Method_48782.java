/** 
 * Get Configuration according to supplied graphName mapped to a specific {@link Graph}; if does not exist, return null.
 * @return Map&lt;String, Object&gt;
 */
public Map<String,Object> getConfiguration(final String configName){
  final List<Map<Object,Object>> graphConfiguration=graph.traversal().V().has(PROPERTY_GRAPH_NAME,configName).valueMap().toList();
  if (graphConfiguration.isEmpty())   return null;
 else   if (graphConfiguration.size() > 1) {
    log.warn("Your configuration management graph is an a bad state. Please " + "ensure you have just one configuration per graph. The behavior " + "of the class' APIs are henceforth unpredictable until this is fixed.");
  }
  return deserializeVertexProperties(graphConfiguration.get(0));
}
