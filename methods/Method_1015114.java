/** 
 * @param from  the source of the edge
 * @param to    the destination of the edge
 * @param edge  the value of the edge
 * @param merge the merge function for the edge values, if an edge already exists
 * @return a graph containing the new edge
 * @throws CycleException if the new edge creates a cycle
 */
@Override public DirectedAcyclicGraph<V,E> link(V from,V to,E edge,BinaryOperator<E> merge){
  boolean newFrom=!vertices().contains(from), newTo=!vertices().contains(to);
  if (!newFrom && !newTo && !out(from).contains(to) && createsCycle(from,to)) {
    throw new CycleException();
  }
  DirectedGraph<V,E> graphPrime=graph.link(from,to,edge,merge);
  Set<V> topPrime=top.remove(to);
  Set<V> bottomPrime=bottom.remove(from);
  if (newFrom) {
    topPrime=top.add(from);
  }
  if (newTo) {
    bottomPrime=bottom.add(to);
  }
  if (isLinear()) {
    graph=graphPrime;
    top=topPrime;
    bottom=bottomPrime;
    return this;
  }
 else {
    return new DirectedAcyclicGraph<>(graphPrime,topPrime,bottomPrime);
  }
}
