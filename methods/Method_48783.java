/** 
 * Get a list of all Configurations, excluding the template configuration; if none exist, return an empty list
 * @return List&lt;Map&lt;String, Object&gt;&gt;
 */
public List<Map<String,Object>> getConfigurations(){
  final List<Map<Object,Object>> graphConfigurations=graph.traversal().V().has(PROPERTY_TEMPLATE,false).valueMap().toList();
  return graphConfigurations.stream().map(this::deserializeVertexProperties).collect(Collectors.toList());
}
