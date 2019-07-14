/** 
 * Update configuration corresponding to supplied graphName; we update supplied existing properties and add new ones to the  {@link Configuration}; The supplied  {@link Configuration} must include aproperty "graph.graphname" and it must match supplied graphName; NOTE: The updated configuration is only guaranteed to take effect if the  {@link Graph} corresponding tographName has been closed and reopened on every JanusGraph Node.
 */
public static void updateConfiguration(final String graphName,final Configuration config){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  removeGraphFromCache(graphName);
  configManagementGraph.updateConfiguration(graphName,config);
}
