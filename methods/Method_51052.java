/** 
 * Get an iterator over all preceding siblings.
 * @param contextNode The context node for the preceding sibling axis.
 * @return A possibly-empty iterator (not null).
 */
@Override public Iterator<Node> getPrecedingSiblingAxisIterator(Object contextNode){
  return new NodeIterator((Node)contextNode){
    @Override protected Node getFirstNode(    Node node){
      return getNextNode(node);
    }
    @Override protected Node getNextNode(    Node node){
      return getPreviousSibling(node);
    }
  }
;
}
