/** 
 * Resolves all variables.
 */
public static void resolveAllVariables(final Properties prop){
  for (  Object o : prop.keySet()) {
    String key=(String)o;
    String value=resolveProperty(prop,key);
    prop.setProperty(key,value);
  }
}
