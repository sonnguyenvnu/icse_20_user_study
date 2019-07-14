/** 
 * Returns an array of all children elements.
 */
public Element[] getChildElements(){
  initChildElementNodes();
  return childElementNodes.clone();
}
