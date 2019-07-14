@Override public void getQueries(QueryContainer queries){
  Set<MessageScope> previousScopes=vertexMemory.getPreviousScopes();
  if (vertexProgram instanceof TraversalVertexProgram || vertexProgram instanceof ShortestPathVertexProgram || vertexProgram instanceof ConnectedComponentVertexProgram || previousScopes.contains(globalScope)) {
    queries.addQuery().direction(Direction.BOTH).edges();
  }
  for (  MessageScope scope : previousScopes) {
    if (scope instanceof MessageScope.Local) {
      JanusGraphVertexStep<Vertex> startStep=FulgoraUtil.getReverseJanusGraphVertexStep((MessageScope.Local)scope,queries.getTransaction());
      QueryContainer.QueryBuilder qb=queries.addQuery();
      startStep.makeQuery(qb);
      qb.edges();
    }
  }
}
