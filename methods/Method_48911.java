@Override public Stream<Result<JanusGraphVertex>> vertexStream(){
  setPrefixInternal(VERTEX_PREFIX);
  return execute(ElementCategory.VERTEX,JanusGraphVertex.class);
}
