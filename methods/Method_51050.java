/** 
 * Get a (single-member) iterator over this node's parent.
 * @param contextNode the context node for the parent axis.
 * @return A possibly-empty iterator (not null).
 */
@Override public Iterator<Node> getParentAxisIterator(Object contextNode){
  if (isAttribute(contextNode)) {
    return new SingleObjectIterator(((Attribute)contextNode).getParent());
  }
  Node parent=((Node)contextNode).jjtGetParent();
  if (parent != null) {
    return new SingleObjectIterator(parent);
  }
 else {
    return EMPTY_ITERATOR;
  }
}
