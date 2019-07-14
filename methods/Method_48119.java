/** 
 * Create a configuration according to the supplied  {@link Configuration}; you must include the property "graph.graphname" with a value in the configuration; you can then open your graph using graph.graphname without having to supply the Configuration or File each time using the  {@link org.janusgraph.core.ConfiguredGraphFactory}.
 */
public static void createConfiguration(final Configuration config){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  configManagementGraph.createConfiguration(config);
}
