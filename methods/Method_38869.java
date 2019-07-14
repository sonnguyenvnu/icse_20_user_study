/** 
 * Removes this node from DOM tree.
 */
public void detachFromParent(){
  if (parentNode == null) {
    return;
  }
  if (parentNode.childNodes != null) {
    parentNode.childNodes.remove(siblingIndex);
    parentNode.reindexChildren();
  }
  parentNode=null;
}
