/** 
 * Returns property with resolved variables.
 */
public static String resolveProperty(final Map map,final String key){
  String value=getProperty(map,key);
  if (value == null) {
    return null;
  }
  value=stp.parse(value,macroName -> getProperty(map,macroName));
  return value;
}
