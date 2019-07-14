/** 
 * Return the #PCDATA content of the element. If the element has a combination of #PCDATA content and child elements, the #PCDATA sections can be retrieved as unnamed child objects. In this case, this method returns null.
 * @webref xml:method
 * @brief Gets the content of an element
 * @return the content.
 * @see XML#getIntContent()
 * @see XML#getFloatContent()
 */
public String getContent(){
  return node.getTextContent();
}
