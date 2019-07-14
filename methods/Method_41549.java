/** 
 * <p> Adds the given <code>Long</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,Long value){
  String strValue=value.toString();
  super.put(key,strValue);
}
