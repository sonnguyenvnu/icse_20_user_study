/** 
 * Returns number of child <b>elements</b> with given name.
 */
public int getChildElementsCount(final String elementName){
  Node lastChild=getLastChildElement(elementName);
  return lastChild.siblingNameIndex + 1;
}
