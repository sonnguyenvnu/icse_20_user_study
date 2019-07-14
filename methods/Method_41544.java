/** 
 * <p> Adds the given <code>double</code> value as a string version to the <code>Job</code>'s data map. </p>
 */
public void putAsString(String key,double value){
  String strValue=Double.toString(value);
  super.put(key,strValue);
}
