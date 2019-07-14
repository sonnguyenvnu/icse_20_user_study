/** 
 * <p> Adds the given <code>float</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,float value){
  String strValue=Float.toString(value);
  super.put(key,strValue);
}
