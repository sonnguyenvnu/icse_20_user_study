public void commitOrRollback(Set<String> graphSourceNamesToCloseTxOn,Boolean commit){
  graphSourceNamesToCloseTxOn.forEach(e -> {
    final Graph graph=getGraph(e);
    if (null != graph) {
      closeTx(graph,commit);
    }
  }
);
}
