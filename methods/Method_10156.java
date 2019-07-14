/** 
 * Escapes string property in the specified JSON object.
 * @param jsonObject the specified JSON object
 */
public static void escapeHTML(final JSONObject jsonObject){
  final Iterator<String> keys=jsonObject.keys();
  while (keys.hasNext()) {
    final String key=keys.next();
    if (jsonObject.opt(key) instanceof String) {
      jsonObject.put(key,Encode.forHtml(jsonObject.optString(key)));
    }
  }
}
