/** 
 * Clones a JSON object list from the specified source object list.
 * @param src the specified source object list
 * @return cloned object list
 */
public static List<JSONObject> clone(final List<JSONObject> src){
  return src.stream().map(JSONs::clone).collect(Collectors.toList());
}
