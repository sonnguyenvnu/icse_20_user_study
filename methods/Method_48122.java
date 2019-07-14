/** 
 * Remove Configuration according to graphName
 */
public static void removeConfiguration(final String graphName){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  removeGraphFromCache(graphName);
  configManagementGraph.removeConfiguration(graphName);
}
