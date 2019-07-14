@Override public Iterable<JanusGraphIndex> getGraphIndexes(final Class<? extends Element> elementType){
  return StreamSupport.stream(QueryUtil.getVertices(transaction,BaseKey.SchemaCategory,JanusGraphSchemaCategory.GRAPHINDEX).spliterator(),false).map(janusGraphVertex -> {
    assert janusGraphVertex instanceof JanusGraphSchemaVertex;
    return ((JanusGraphSchemaVertex)janusGraphVertex).asIndexType();
  }
).filter(indexType -> indexType.getElement().subsumedBy(elementType)).map(JanusGraphIndexWrapper::new).collect(Collectors.toList());
}
