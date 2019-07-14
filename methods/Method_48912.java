@Override public Stream<Result<JanusGraphEdge>> edgeStream(){
  setPrefixInternal(EDGE_PREFIX);
  return execute(ElementCategory.EDGE,JanusGraphEdge.class);
}
