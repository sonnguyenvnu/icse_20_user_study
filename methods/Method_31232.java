/** 
 * Puts this property in the config if it has been set in any of these values.
 * @param config The config.
 * @param key    The property name.
 * @param values The values to try. The first non-null value will be set.
 */
public static void putArrayIfSet(Map<String,String> config,String key,String[]... values){
  for (  String[] value : values) {
    if (value != null) {
      config.put(key,StringUtils.arrayToCommaDelimitedString(value));
      return;
    }
  }
}
