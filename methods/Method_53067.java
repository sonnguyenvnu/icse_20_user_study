/** 
 * Returns true if this object has a mapping for  {@code name}. The mapping may be  {@link #NULL}.
 * @param name The name of the value to check on.
 * @return true if this object has a field named {@code name}
 */
public boolean has(String name){
  return nameValuePairs.containsKey(name);
}
