/** 
 * @return a singleton list of the graph, unsplit.  The graph can be split into separate pieces in linear time using {@code Graphs.connectedComponents()}.
 */
default IList<? extends IGraph<V,E>> split(int parts){
  return List.of(this);
}
