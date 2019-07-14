/** 
 * Returns the number of edges that match this query
 * @return Number of edges that match this query
 */
default long edgeCount(){
  return vertexIds().size();
}
