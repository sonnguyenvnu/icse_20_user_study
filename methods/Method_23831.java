/** 
 * @webref xml:method
 * @brief Appends a new child to the element
 */
public XML addChild(String tag){
  Document document=node.getOwnerDocument();
  Node newChild=document.createElement(tag);
  return appendChild(newChild);
}
