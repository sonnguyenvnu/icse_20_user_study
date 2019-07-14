/** 
 * Opens a  {@link JanusGraph} database configured according to the provided configuration.This method shouldn't be called by end users; it is used by internal server processes to open graphs defined at server start that do not include the graphname property.
 * @param configuration Configuration for the graph database
 * @param backupName Backup name for graph
 * @return JanusGraph graph database
 */
public static JanusGraph open(ReadConfiguration configuration,String backupName){
  final ModifiableConfiguration config=new ModifiableConfiguration(ROOT_NS,(WriteConfiguration)configuration,BasicConfiguration.Restriction.NONE);
  final String graphName=config.has(GRAPH_NAME) ? config.get(GRAPH_NAME) : backupName;
  final JanusGraphManager jgm=JanusGraphManagerUtility.getInstance();
  if (null != graphName) {
    Preconditions.checkNotNull(jgm,JANUS_GRAPH_MANAGER_EXPECTED_STATE_MSG);
    return (JanusGraph)jgm.openGraph(graphName,gName -> new StandardJanusGraph(new GraphDatabaseConfigurationBuilder().build(configuration)));
  }
 else {
    if (jgm != null) {
      log.warn("You should supply \"graph.graphname\" in your .properties file configuration if you are opening " + "a graph that has not already been opened at server start, i.e. it was " + "defined in your YAML file. This will ensure the graph is tracked by the JanusGraphManager, " + "which will enable autocommit and rollback functionality upon all gremlin script executions. " + "Note that JanusGraphFactory#open(String === shortcut notation) does not support consuming the property " + "\"graph.graphname\" so these graphs should be accessed dynamically by supplying a .properties file here " + "or by using the ConfiguredGraphFactory.");
    }
    return new StandardJanusGraph(new GraphDatabaseConfigurationBuilder().build(configuration));
  }
}
