/** 
 * <p> Retrieve the identified <code>Character</code> value from the <code>JobDataMap</code>. </p>
 * @throws ClassCastException if the identified object is not a String.
 */
public Character getCharacterFromString(String key){
  Object obj=get(key);
  return ((String)obj).charAt(0);
}
