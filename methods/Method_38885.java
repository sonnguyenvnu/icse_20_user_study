/** 
 * Filters child nodes.
 */
public Node[] filterChildNodes(final Predicate<Node> nodePredicate){
  if (childNodes == null) {
    return new Node[0];
  }
  return childNodes.stream().filter(nodePredicate).toArray(Node[]::new);
}
