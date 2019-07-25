/** 
 * Creates a UrlEncodedQueryString from the given Map. <p> The order the parameters are created in corresponds to the iteration order of the Map.
 * @param parameterMap <code>Map</code> containing parameter names and values.
 */
public static UrlEncodedQueryStringBase create(Map<String,List<String>> parameterMap){
  UrlEncodedQueryStringBase queryString=new UrlEncodedQueryStringBase();
  for (  Map.Entry<String,List<String>> entry : parameterMap.entrySet()) {
    queryString.queryMap.put(entry.getKey(),new ArrayList<String>(entry.getValue()));
  }
  return queryString;
}
