/** 
 * Mark a vetex and its connected vertices as visited.
 * @param v vertex
 */
private void relax(int v){
  visited.set(v);
  List<Integer> children=graph.get(v);
  if (children != null) {
    for (    int c : children)     visited.set(c);
  }
}
