/** 
 * Returns String property from a map.
 * @see #getProperty(java.util.Map,String) 
 */
public static String getProperty(final Map map,final String key,final String defaultValue){
  Object val=map.get(key);
  return (val instanceof String) ? (String)val : defaultValue;
}
