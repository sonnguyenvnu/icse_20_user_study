private void updateTraversalSource(String graphName,Graph graph){
  if (null != gremlinExecutor) {
    updateTraversalSource(graphName,graph,gremlinExecutor,this);
  }
}
