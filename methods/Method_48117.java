/** 
 * Removes the graph corresponding to the supplied graphName from the  {@link JanusGraphManager} graph reference tracker andreturns the corresponding Graph, or null if it doesn't exist.
 * @param graphName Graph
 * @return JanusGraph
 */
public static JanusGraph close(String graphName) throws Exception {
  final JanusGraphManager jgm=JanusGraphManagerUtility.getInstance();
  Preconditions.checkNotNull(jgm,JANUS_GRAPH_MANAGER_EXPECTED_STATE_MSG);
  final Graph graph=jgm.removeGraph(graphName);
  if (null != graph)   graph.close();
  return (JanusGraph)graph;
}
