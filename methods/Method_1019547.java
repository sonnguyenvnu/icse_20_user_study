/** 
 * Updates this sketch with a double key and double values. The values will be stored or added to the ones associated with the key
 * @param key The given double key
 * @param values The given values
 */
public void update(final double key,final double[] values){
  update(Util.doubleToLongArray(key),values);
}
