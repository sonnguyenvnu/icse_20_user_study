/** 
 * Puts an object to the definitions
 * @param key a string representation of the key
 * @param val the object to be stored
 */
public void put(String key,Object val){
  this.put(UniqueString.uniqueStringOf(key),val);
}
