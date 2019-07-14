/** 
 * <p> Adds the given <code>long</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,long value){
  String strValue=Long.valueOf(value).toString();
  super.put(key,strValue);
}
