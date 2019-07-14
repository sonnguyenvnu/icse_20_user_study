/** 
 * Drop graph database, deleting all data in storage and indexing backends. Graph can be open or closed (will be closed as part of the drop operation). The graph is removed from the  {@link JanusGraphManager} graph reference tracker, if there. Finally, if a configuration for thisgraph exists on the  {@link ConfigurationManagementGraph}, then said configuration will be removed. <p><b>WARNING: This is an irreversible operation that will delete all graph and index data.</b></p>
 * @param graphName String graphName. Corresponding graph can be open or closed.
 * @throws BackendException If an error occurs during deletion
 * @throws ConfigurationManagementGraphNotEnabledException If ConfigurationManagementGraph not
 */
public static void drop(String graphName) throws BackendException, ConfigurationManagementGraphNotEnabledException, Exception {
  final StandardJanusGraph graph=(StandardJanusGraph)ConfiguredGraphFactory.close(graphName);
  JanusGraphFactory.drop(graph);
  removeConfiguration(graphName);
}
