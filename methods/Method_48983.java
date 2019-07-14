@Override public VertexList vertexIds(){
  return execute(RelationCategory.EDGE,new VertexIdConstructor());
}
