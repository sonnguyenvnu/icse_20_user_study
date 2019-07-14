/** 
 * Returns whether the type is known to the receiver.
 * @param type Class
 * @return boolean
 */
public boolean contains(Class<?> type){
  return typesByName.containsValue(type);
}
