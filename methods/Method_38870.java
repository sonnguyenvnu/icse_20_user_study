/** 
 * Inserts node at given index.
 */
public void insertChild(final Node node,final int index){
  node.detachFromParent();
  node.parentNode=this;
  try {
    initChildNodes(node);
    childNodes.add(index,node);
  }
 catch (  IndexOutOfBoundsException ignore) {
    throw new LagartoDOMException("Invalid node index: " + index);
  }
  reindexChildren();
}
