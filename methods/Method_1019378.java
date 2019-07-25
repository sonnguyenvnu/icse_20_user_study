/** 
 * Determines whether this model contains the specified element.
 * @param element any element
 * @return true, if the element is contained in this model
 */
public boolean contains(Element element){
  return elementsById.values().contains(element);
}
