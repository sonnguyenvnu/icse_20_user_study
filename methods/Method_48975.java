@Override public Map<JanusGraphVertex,VertexList> vertexIds(){
  return execute(RelationCategory.EDGE,new VertexIdConstructor());
}
