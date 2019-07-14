/** 
 * Get an iterator over all following nodes, depth-first.
 * @param contextNode The context node for the following axis.
 * @return A possibly-empty iterator (not null).
 */
@Override public Iterator<Node> getFollowingAxisIterator(Object contextNode){
  return new NodeIterator((Node)contextNode){
    @Override protected Node getFirstNode(    Node node){
      if (node == null) {
        return null;
      }
 else {
        Node sibling=getNextSibling(node);
        if (sibling == null) {
          return getFirstNode(node.jjtGetParent());
        }
 else {
          return sibling;
        }
      }
    }
    @Override protected Node getNextNode(    Node node){
      if (node == null) {
        return null;
      }
 else {
        Node n=getFirstChild(node);
        if (n == null) {
          n=getNextSibling(node);
        }
        if (n == null) {
          return getFirstNode(node.jjtGetParent());
        }
 else {
          return n;
        }
      }
    }
  }
;
}
