@Override public boolean nextKeyValue() throws IOException, InterruptedException {
  while (reader.nextKeyValue()) {
    final TinkerVertex maybeNullTinkerVertex=deserializer.readHadoopVertex(reader.getCurrentKey(),reader.getCurrentValue());
    if (null != maybeNullTinkerVertex) {
      vertex=new VertexWritable(maybeNullTinkerVertex);
      if (graphFilter == null) {
        return true;
      }
 else {
        final Optional<StarGraph.StarVertex> vertexWritable=vertex.get().applyGraphFilter(graphFilter);
        if (vertexWritable.isPresent()) {
          vertex.set(vertexWritable.get());
          return true;
        }
      }
    }
  }
  return false;
}
