/** 
 * @return String - the part of the name that is before the (first) colon(":")
 */
public String getNamespacePrefix(){
  int colonIndex=name.indexOf(':');
  return colonIndex >= 0 ? name.substring(0,colonIndex) : "";
}
