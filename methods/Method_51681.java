/** 
 * Returns whether the typeName is known to the receiver.
 * @param typeName String
 * @return boolean
 */
public boolean contains(String typeName){
  return typesByName.containsKey(typeName);
}
