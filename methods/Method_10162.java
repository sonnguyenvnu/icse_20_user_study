/** 
 * Clones a JSON object from the specified source object.
 * @param src the specified source object
 * @return cloned object
 */
public static JSONObject clone(final JSONObject src){
  return new JSONObject(src,CollectionUtils.jsonArrayToArray(src.names(),String[].class));
}
