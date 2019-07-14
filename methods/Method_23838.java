/** 
 * @webref xml:method
 * @brief Sets the content of an attribute as a String
 */
public void setString(String name,String value){
  ((Element)node).setAttribute(name,value);
}
