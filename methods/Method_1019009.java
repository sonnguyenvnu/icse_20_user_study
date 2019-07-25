/** 
 * @return Pair of the vertices.
 */
public Pair<Vertex<T>,Vertex<T>> verticies(){
  return new Pair<>(getParent(),getChild());
}
