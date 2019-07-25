@Override public Iterable<Row> apply(final GraphFrame graphFrame){
  final Iterator<Row> vertices=graphFrame.vertices().toLocalIterator();
  final Iterator<Row> edges=graphFrame.edges().toLocalIterator();
  return () -> Iterators.concat(vertices,edges);
}
