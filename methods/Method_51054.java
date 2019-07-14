/** 
 * Get an iterator over all preceding nodes, depth-first.
 * @param contextNode The context node for the preceding axis.
 * @return A possibly-empty iterator (not null).
 */
@Override public Iterator<Node> getPrecedingAxisIterator(Object contextNode){
  return new NodeIterator((Node)contextNode){
    @Override protected Node getFirstNode(    Node node){
      if (node == null) {
        return null;
      }
 else {
        Node sibling=getPreviousSibling(node);
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
        Node n=getLastChild(node);
        if (n == null) {
          n=getPreviousSibling(node);
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
