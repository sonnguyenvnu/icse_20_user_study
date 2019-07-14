/** 
 * <p> Adds the given <code>Float</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,Float value){
  String strValue=value.toString();
  super.put(key,strValue);
}
