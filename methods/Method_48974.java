@Override public Map<JanusGraphVertex,Iterable<JanusGraphVertex>> vertices(){
  return execute(RelationCategory.EDGE,new VertexConstructor());
}
