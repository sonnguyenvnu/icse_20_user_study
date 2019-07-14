@Override public FulgoraGraphComputer compute() throws IllegalArgumentException {
  StandardJanusGraph graph=(StandardJanusGraph)this;
  return new FulgoraGraphComputer(graph,graph.getConfiguration().getConfiguration());
}
