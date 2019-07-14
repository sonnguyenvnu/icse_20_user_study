/** 
 * Put the names of all children into an array. Same as looping through each child and calling getName() on each XMLElement.
 * @webref xml:method
 * @brief Returns the names of all children as an array
 */
public String[] listChildren(){
  checkChildren();
  String[] outgoing=new String[children.length];
  for (int i=0; i < children.length; i++) {
    outgoing[i]=children[i].getName();
  }
  return outgoing;
}
