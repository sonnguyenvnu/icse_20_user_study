/** 
 * @return null If there isn't any child.
 */
public AbstractReportNode getFirstChild(){
  if (this.isLeaf()) {
    return null;
  }
  return this.childNodes.get(0);
}
