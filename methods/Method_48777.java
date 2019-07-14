/** 
 * Create a template configuration according to the supplied  {@link Configuration}; if you already created a template configuration or the supplied  {@link Configuration}contains the property "graph.graphname", we throw a  {@link RuntimeException}; you can then use this template configuration to create a graph using the ConfiguredGraphFactory create signature and supplying a new graphName.
 */
public void createTemplateConfiguration(final Configuration config){
  Preconditions.checkArgument(!config.containsKey(PROPERTY_GRAPH_NAME),String.format("Your template configuration may not contain the property \"%s\".",PROPERTY_GRAPH_NAME));
  Preconditions.checkState(null == getTemplateConfiguration(),"You may only have one template configuration and one exists already.");
  final Map<Object,Object> map=ConfigurationConverter.getMap(config);
  final Vertex v=graph.addVertex();
  v.property(PROPERTY_TEMPLATE,true);
  map.forEach((key,value) -> v.property((String)key,value));
  graph.tx().commit();
}
