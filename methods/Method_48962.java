private Iterable<JanusGraphVertex> executeIndividualVertices(InternalVertex vertex,BaseVertexCentricQuery baseQuery){
  VertexCentricQuery query=constructQuery(vertex,baseQuery);
  if (useSimpleQueryProcessor(query,vertex))   return new SimpleVertexQueryProcessor(query,tx).vertexIds();
 else   return edges2Vertices((Iterable)executeIndividualRelations(vertex,baseQuery),query.getVertex());
}
