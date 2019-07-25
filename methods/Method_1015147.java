/** 
 * @param graph a directed graph
 * @return a list of all cyclical paths through the graph
 */
public static <V,E>List<List<V>> cycles(IGraph<V,E> graph){
  if (!graph.isDirected()) {
    throw new IllegalArgumentException("graph must be directed");
  }
  LinearList<V> path=new LinearList<>();
  IList<Iterator<V>> branches=new LinearList<>();
  LinearSet<V> blocked=new LinearSet<>(graph.vertexHash(),graph.vertexEquality());
  LinearMap<V,ISet<V>> blocking=new LinearMap<>();
  List<List<V>> result=new List<List<V>>().linear();
  for (  IGraph<V,E> subgraph : stronglyConnectedSubgraphs(graph,true)) {
    if (subgraph.vertices().stream().allMatch(v -> subgraph.out(v).size() == 1)) {
      V seed=subgraph.vertices().nth(0);
      result.addLast(List.from(bfsVertices(seed,subgraph::out)).addLast(seed));
      continue;
    }
    for (    V seed : subgraph.vertices()) {
      long threshold=subgraph.indexOf(seed);
      path.addLast(seed);
      branches.addLast(subgraph.out(seed).iterator());
      blocked.clear();
      blocking.clear();
      int depth=1;
      do {
        if (branches.last().hasNext()) {
          V v=branches.last().next();
          if (subgraph.indexOf(v) < threshold) {
            continue;
          }
          if (subgraph.vertexEquality().test(seed,v)) {
            result.addLast(List.from(path).addLast(seed));
            depth=0;
          }
 else           if (!blocked.contains(v)) {
            path.addLast(v);
            depth++;
            branches.addLast(subgraph.out(v).iterator());
          }
          blocked.add(v);
        }
 else {
          V v=path.popLast();
          depth=max(-1,depth - 1);
          if (depth < 0) {
            LinearList<V> stack=new LinearList<V>().addFirst(v);
            while (stack.size() > 0) {
              V u=stack.popLast();
              if (blocked.contains(u)) {
                blocked.remove(u);
                blocking.get(u,(ISet<V>)Sets.EMPTY).forEach(stack::addLast);
                blocking.remove(u);
              }
            }
          }
 else {
            graph.out(v).forEach(u -> blocking.getOrCreate(u,LinearSet::new).add(v));
          }
          branches.removeLast();
        }
      }
 while (path.size() > 0);
    }
  }
  return result.forked();
}
