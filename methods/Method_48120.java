/** 
 * Create a template configuration according to the supplied  {@link Configuration}; if you already created a template configuration or the supplied  {@link Configuration}contains the property "graph.graphname", we throw a  {@link RuntimeException}; you can then use this template configuration to create a graph using the ConfiguredGraphFactory create signature and supplying a new graphName.
 */
public static void createTemplateConfiguration(final Configuration config){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  configManagementGraph.createTemplateConfiguration(config);
}
