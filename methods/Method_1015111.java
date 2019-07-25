/** 
 * @return a directed acyclic graph equivalent to {@code graph}
 * @throws CycleException if {@code graph} contains a cycle
 */
public static <V,E>DirectedAcyclicGraph<V,E> from(DirectedGraph<V,E> graph){
  if (Graphs.stronglyConnectedComponents(graph,false).size() > 0) {
    throw new CycleException();
  }
  Set<V> top=new Set<>(graph.vertexHash(),graph.vertexEquality()).linear();
  Set<V> bottom=new Set<>(graph.vertexHash(),graph.vertexEquality()).linear();
  graph.vertices().stream().filter(v -> graph.in(v).size() == 0).forEach(top::add);
  graph.vertices().stream().filter(v -> graph.out(v).size() == 0).forEach(bottom::add);
  return new DirectedAcyclicGraph<>(graph,top.forked(),bottom.forked());
}
