/** 
 * Extract a map from a query string.
 * @param query a query (or fragment) string from a URI
 * @return a Map of the values in the query
 */
public static Map<String,String> extractMap(String query){
  Map<String,String> map=new HashMap<String,String>();
  Properties properties=StringUtils.splitArrayElementsIntoProperties(StringUtils.delimitedListToStringArray(query,"&"),"=");
  if (properties != null) {
    for (    Object key : properties.keySet()) {
      map.put(key.toString(),properties.get(key).toString());
    }
  }
  return map;
}
