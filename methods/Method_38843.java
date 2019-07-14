/** 
 * Fixes foster elements.
 */
public void fixFosterElements(final Document document){
  findFosterNodes(document);
  fixElements();
  fixText();
}
