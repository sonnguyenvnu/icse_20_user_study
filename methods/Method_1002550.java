/** 
 * Retrieve the corresponding  {@link Node} for the specified object.
 * @param o the target object
 * @param < T > type of the target object
 * @return {@link Node} for the specified object
 */
@SuppressWarnings("unchecked") public <T>Node<T> get(T o){
  Node<T> node=(Node<T>)_nodes.get(o);
  if (node == null) {
    node=new Node<T>(o);
    _nodes.put(o,node);
  }
  return node;
}
