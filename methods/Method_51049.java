/** 
 * Get an iterator over all of this node's children.
 * @param contextNode The context node for the child axis.
 * @return A possibly-empty iterator (not null).
 */
@Override public Iterator<Node> getChildAxisIterator(Object contextNode){
  return new NodeIterator((Node)contextNode){
    @Override protected Node getFirstNode(    Node node){
      return getFirstChild(node);
    }
    @Override protected Node getNextNode(    Node node){
      return getNextSibling(node);
    }
  }
;
}
