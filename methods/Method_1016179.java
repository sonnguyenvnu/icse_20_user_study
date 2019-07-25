/** 
 * Returns user objects collected from specified nodes.
 * @param nodes list of nodes to collect user objects from
 * @param < O >   user object type
 * @return user objects collected from specified nodes
 */
protected <O>List<O> collect(final List<N> nodes){
  final List<O> objects=new ArrayList<O>(nodes.size());
  for (  final N node : nodes) {
    objects.add((O)node.getUserObject());
  }
  return objects;
}
