/** 
 * Returns this node's next sibling of <b>any</b> type or <code>null</code> if this is the last sibling.
 */
public Node getNextSibling(){
  List<Node> siblings=parentNode.childNodes;
  int index=siblingIndex + 1;
  if (index >= siblings.size()) {
    return null;
  }
  return siblings.get(index);
}
