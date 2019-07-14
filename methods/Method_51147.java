/** 
 * @return null If there isn't any sibling.
 */
public AbstractReportNode getNextSibling(){
  if (parentNode == null) {
    return null;
  }
  int index=parentNode.getChildIndex(this);
  if (index < 0) {
    return null;
  }
  if (index >= parentNode.childNodes.size() - 1) {
    return null;
  }
  return parentNode.childNodes.get(index + 1);
}
