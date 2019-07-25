/** 
 * Utility for test classes, so that they can inline json in a test class. Does a character level replacement of apostrophe (') with double quote ("). This means you can express a snippit of JSON without having to forward slash escape everything. This is character based, so don't have any apostrophes (') in your test data.
 * @param javason JSON-ish string you want to turn into Maps-of-Maps
 * @return Maps-of-Maps
 */
public static Map<String,Object> javason(String javason){
  String json=javason.replace('\'','"');
  return jsonToMap(new ByteArrayInputStream(json.getBytes()));
}
