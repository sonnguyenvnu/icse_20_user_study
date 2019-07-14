private void updateTraversalSource(String graphName,Graph graph,GremlinExecutor gremlinExecutor,JanusGraphManager graphManager){
  gremlinExecutor.getScriptEngineManager().put(graphName,graph);
  String traversalName=graphName + "_traversal";
  TraversalSource traversalSource=graph.traversal();
  gremlinExecutor.getScriptEngineManager().put(traversalName,traversalSource);
  graphManager.putTraversalSource(traversalName,traversalSource);
}
