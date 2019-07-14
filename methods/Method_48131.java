/** 
 * Drop graph database, deleting all data in storage and indexing backends. Graph can be open or closed (will be closed as part of the drop operation). The graph is also removed from the  {@link JanusGraphManager}graph reference tracker, if there. <p><b>WARNING: This is an irreversible operation that will delete all graph and index data.</b></p>
 * @param graph JanusGraph graph database. Can be open or closed.
 * @throws BackendException If an error occurs during deletion
 */
public static void drop(JanusGraph graph) throws BackendException {
  Preconditions.checkNotNull(graph);
  Preconditions.checkArgument(graph instanceof StandardJanusGraph,"Invalid graph instance detected: %s",graph.getClass());
  final StandardJanusGraph g=(StandardJanusGraph)graph;
  final JanusGraphManager jgm=JanusGraphManagerUtility.getInstance();
  if (jgm != null) {
    jgm.removeGraph(g.getGraphName());
  }
  if (graph.isOpen()) {
    graph.close();
  }
  final GraphDatabaseConfiguration config=g.getConfiguration();
  final Backend backend=config.getBackend();
  try {
    backend.clearStorage();
  }
  finally {
    IOUtils.closeQuietly(backend);
  }
}
