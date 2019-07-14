private void initialize(){
  assert !initialized;
  initialized=true;
  if (!starts.hasNext()) {
    throw FastNoSuchElementException.instance();
  }
  List<Traverser.Admin<Edge>> edges=new ArrayList<>();
  Set<Vertex> vertices=Sets.newHashSet();
  starts.forEachRemaining(e -> {
    edges.add(e);
    if (vertices.size() < txVertexCacheSize) {
      if (Direction.IN.equals(direction) || Direction.BOTH.equals(direction)) {
        vertices.add(e.get().inVertex());
      }
      if (Direction.OUT.equals(direction) || Direction.BOTH.equals(direction)) {
        vertices.add(e.get().outVertex());
      }
    }
  }
);
  if (vertices.size() > 1) {
    JanusGraphMultiVertexQuery multiQuery=JanusGraphTraversalUtil.getTx(traversal).multiQuery();
    ((BasicVertexCentricQueryBuilder)multiQuery).profiler(queryProfiler);
    multiQuery.addAllVertices(vertices).preFetch();
  }
  starts.add(edges.iterator());
}
