@Override public FulgoraGraphComputer compute() throws IllegalArgumentException {
  JanusGraphBlueprintsGraph graph=getGraph();
  if (isOpen())   commit();
  return graph.compute();
}
