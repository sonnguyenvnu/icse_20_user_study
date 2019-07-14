public static JanusGraphTransaction getTx(Traversal.Admin<?,?> traversal){
  final JanusGraphTransaction tx;
  Optional<Graph> optGraph=TraversalHelper.getRootTraversal(traversal.asAdmin()).getGraph();
  if (traversal instanceof FulgoraElementTraversal) {
    tx=(JanusGraphTransaction)optGraph.get();
  }
 else {
    if (!optGraph.isPresent())     throw new IllegalArgumentException("Traversal is not bound to a graph: " + traversal);
    Graph graph=optGraph.get();
    if (graph instanceof JanusGraphTransaction)     tx=(JanusGraphTransaction)graph;
 else     if (graph instanceof JanusGraphBlueprintsGraph)     tx=((JanusGraphBlueprintsGraph)graph).getCurrentThreadTx();
 else     throw new IllegalArgumentException("Traversal is not bound to a JanusGraph Graph, but: " + graph);
  }
  if (tx == null)   throw new IllegalArgumentException("Not a valid start step for a JanusGraph traversal: " + traversal);
  if (tx.isOpen())   return tx;
 else   return ((StandardJanusGraphTx)tx).getNextTx();
}
