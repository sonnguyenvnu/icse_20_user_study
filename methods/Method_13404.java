/** 
 * Get Parameters from the specified query string. <p>
 * @param queryString The query string
 * @return The query parameters
 */
public static MultiValueMap<String,String> getParameters(String queryString){
  return getParameters(delimitedListToStringArray(queryString,AND));
}
