/** 
 * @webref xml:method
 * @brief Sets the element's name
 */
public void setName(String newName){
  Document document=node.getOwnerDocument();
  node=document.renameNode(node,null,newName);
}
