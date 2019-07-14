@Override protected Iterator<E> flatMap(final Traverser.Admin<Vertex> traverser){
  Iterable<? extends JanusGraphElement> result;
  if (useMultiQuery) {
    if (multiQueryResults == null || !multiQueryResults.containsKey(traverser.get())) {
      initializeMultiQuery(Arrays.asList(traverser));
    }
    result=multiQueryResults.get(traverser.get());
  }
 else {
    final JanusGraphVertexQuery query=makeQuery((JanusGraphTraversalUtil.getJanusGraphVertex(traverser)).query());
    result=(Vertex.class.isAssignableFrom(getReturnClass())) ? query.vertices() : query.edges();
  }
  if (batchPropertyPrefetching) {
    Set<Vertex> vertices=Sets.newHashSet();
    result.forEach(v -> {
      if (vertices.size() < txVertexCacheSize) {
        vertices.add((Vertex)v);
      }
    }
);
    if (vertices.size() > 1) {
      JanusGraphMultiVertexQuery propertyMultiQuery=JanusGraphTraversalUtil.getTx(traversal).multiQuery();
      ((BasicVertexCentricQueryBuilder)propertyMultiQuery).profiler(queryProfiler);
      propertyMultiQuery.addAllVertices(vertices).preFetch();
    }
  }
  return (Iterator<E>)result.iterator();
}
