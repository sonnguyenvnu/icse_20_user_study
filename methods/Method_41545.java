/** 
 * <p> Adds the given <code>Double</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,Double value){
  String strValue=value.toString();
  super.put(key,strValue);
}
