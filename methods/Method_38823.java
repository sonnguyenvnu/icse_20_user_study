/** 
 * Remove the parents of the set of matched elements from the DOM, leaving  the matched elements (and siblings, if any) in their place. 
 */
public Jerry unwrap(){
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    Node parent=node.getParentNode();
    if (parent == null) {
      continue;
    }
    Node grandparent=parent.getParentNode();
    if (grandparent == null) {
      continue;
    }
    Node[] siblings=parent.getChildNodes();
    int index=parent.getSiblingIndex();
    grandparent.insertChild(siblings,index);
    parent.detachFromParent();
  }
  return this;
}
