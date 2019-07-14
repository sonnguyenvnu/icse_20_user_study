/** 
 * <p> Adds the given <code>boolean</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,boolean value){
  String strValue=Boolean.valueOf(value).toString();
  super.put(key,strValue);
}
