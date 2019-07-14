@Override public void createSchema(){
  LOGGER.info("creating schema");
  final TinkerGraph tinkerGraph=(TinkerGraph)graph;
  if (!tinkerGraph.getIndexedKeys(Vertex.class).iterator().hasNext()) {
    tinkerGraph.createIndex("name",Vertex.class);
  }
}
