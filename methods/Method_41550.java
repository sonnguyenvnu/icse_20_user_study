/** 
 * <p> Retrieve the identified <code>int</code> value from the <code>JobDataMap</code>. </p>
 * @throws ClassCastException if the identified object is not a String.
 */
public int getIntFromString(String key){
  Object obj=get(key);
  return new Integer((String)obj);
}
