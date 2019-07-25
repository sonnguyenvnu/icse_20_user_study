/** 
 * Update the json serialiser with the provided custom properties.
 * @param jsonSerialiserClass   the json serialiser class to use (or null to use the default)
 * @param jsonSerialiserModules any extra json serialiser modules required
 * @param strictJson            true if strict json conversion should be used
 */
public static void update(final String jsonSerialiserClass,final String jsonSerialiserModules,final Boolean strictJson){
  if (StringUtils.isNotBlank(jsonSerialiserModules)) {
    final String modulesCsv=new StringDeduplicateConcat().apply(System.getProperty(JSON_SERIALISER_MODULES),jsonSerialiserModules);
    System.setProperty(JSON_SERIALISER_MODULES,modulesCsv);
  }
  if (null != jsonSerialiserClass) {
    System.setProperty(JSON_SERIALISER_CLASS_KEY,jsonSerialiserClass);
  }
  if (null != strictJson) {
    System.setProperty(STRICT_JSON,strictJson.toString());
  }
  update();
}
