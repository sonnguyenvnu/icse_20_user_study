/** 
 * Open a  {@link JanusGraph} using a previously created Configuration using the{@link ConfigurationManagementGraph} API. A corresponding configuration must exist.<p>NOTE: If your configuration corresponding to this graph does not contain information about the backend's keyspace/table/storage directory, then we set the keyspace/table to the graphName or set the storage directory to the storage_root + /graphName.</p>
 * @param graphName
 * @return JanusGraph
 */
public static JanusGraph open(String graphName){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  final Map<String,Object> graphConfigMap=configManagementGraph.getConfiguration(graphName);
  Preconditions.checkNotNull(graphConfigMap,"Please create configuration for this graph using the ConfigurationManagementGraph#createConfiguration API.");
  final JanusGraphManager jgm=JanusGraphManagerUtility.getInstance();
  Preconditions.checkNotNull(jgm,JANUS_GRAPH_MANAGER_EXPECTED_STATE_MSG);
  final CommonsConfiguration config=new CommonsConfiguration(new MapConfiguration(graphConfigMap));
  return (JanusGraph)jgm.openGraph(graphName,(  String gName) -> new StandardJanusGraph(new GraphDatabaseConfigurationBuilder().build(config)));
}
