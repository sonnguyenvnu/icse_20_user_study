@Override public Graph openGraph(String gName,Function<String,Graph> thunk){
  Graph graph=graphs.get(gName);
  if (graph != null && !((StandardJanusGraph)graph).isClosed()) {
    updateTraversalSource(gName,graph);
    return graph;
  }
 else {
synchronized (instantiateGraphLock) {
      graph=graphs.get(gName);
      if (graph == null || ((StandardJanusGraph)graph).isClosed()) {
        graph=thunk.apply(gName);
        graphs.put(gName,graph);
      }
    }
    updateTraversalSource(gName,graph);
    return graph;
  }
}
