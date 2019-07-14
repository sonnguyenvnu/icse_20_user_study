/** 
 * Create a configuration according to the supplied  {@link Configuration}; you must include the property "graph.graphname" with a value in the configuration; you can then open your graph using graph.graphname without having to supply the Configuration or File each time using the  {@link org.janusgraph.core.ConfiguredGraphFactory}.
 */
public void createConfiguration(final Configuration config){
  Preconditions.checkArgument(config.containsKey(PROPERTY_GRAPH_NAME),String.format("Please include the property \"%s\" in your configuration.",PROPERTY_GRAPH_NAME));
  final Map<Object,Object> map=ConfigurationConverter.getMap(config);
  final Vertex v=graph.addVertex(T.label,VERTEX_LABEL);
  map.forEach((key,value) -> v.property((String)key,value));
  v.property(PROPERTY_TEMPLATE,false);
  graph.tx().commit();
}
