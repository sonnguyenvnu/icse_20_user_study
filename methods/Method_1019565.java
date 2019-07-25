/** 
 * Updates this sketch with a double key and U value. The value is passed to update() method of the Summary object associated with the key
 * @param key The given double key
 * @param value The given U value
 */
public void update(final double key,final U value){
  update(Util.doubleToLongArray(key),value);
}
