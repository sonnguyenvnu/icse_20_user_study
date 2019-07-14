/** 
 * Drop graph database, deleting all data in storage and indexing backends. Graph can be open or closed (will be closed as part of the drop operation). The graph is also removed from the  {@link org.janusgraph.graphdb.management.JanusGraphManager}graph reference tracker, if there. <p><b>WARNING: This is an irreversible operation that will delete all graph and index data.</b></p>
 * @param graph
 * @throws BackendException If an error occurs during deletion
 * @deprecated Use {@link org.janusgraph.core.JanusGraphFactory#drop(JanusGraph)}
 */
@Deprecated public static void clear(JanusGraph graph) throws BackendException {
  JanusGraphFactory.drop(graph);
}
