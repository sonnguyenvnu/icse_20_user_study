/** 
 * Converts the JSONObject into a property file object.
 * @param jo JSONObject
 * @return java.util.Properties
 * @throws JSONException
 */
public static Properties toProperties(JSONObject jo) throws JSONException {
  Properties properties=new Properties();
  if (jo != null) {
    for (    final String key : jo.keySet()) {
      Object value=jo.opt(key);
      if (!JSONObject.NULL.equals(value)) {
        properties.put(key,value.toString());
      }
    }
  }
  return properties;
}
