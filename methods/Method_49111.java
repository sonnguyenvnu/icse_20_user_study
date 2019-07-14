private void executeGraphCentryQuery(final GraphCentricQueryBuilder builder,final List<Iterator<E>> responses,final Entry<Integer,GraphCentricQuery> query){
  final Class<? extends JanusGraphElement> classe=Vertex.class.isAssignableFrom(this.returnClass) ? JanusGraphVertex.class : JanusGraphEdge.class;
  final Iterator<E> response=(Iterator<E>)builder.iterables(query.getValue(),classe).iterator();
  long i=0;
  while (i < query.getKey() && response.hasNext()) {
    response.next();
    i++;
  }
  responses.add(response);
}
