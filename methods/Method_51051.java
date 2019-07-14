/** 
 * Get an iterator over all following siblings.
 * @param contextNode the context node for the sibling iterator.
 * @return A possibly-empty iterator (not null).
 */
@Override public Iterator<Node> getFollowingSiblingAxisIterator(Object contextNode){
  return new NodeIterator((Node)contextNode){
    @Override protected Node getFirstNode(    Node node){
      return getNextNode(node);
    }
    @Override protected Node getNextNode(    Node node){
      return getNextSibling(node);
    }
  }
;
}
