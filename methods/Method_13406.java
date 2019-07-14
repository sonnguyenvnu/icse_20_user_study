/** 
 * Get Parameters from the specified pairs of name-value. <p>
 * @param pairs The pairs of name-value
 * @return The query parameters
 */
public static MultiValueMap<String,String> getParameters(String... pairs){
  return getParameters(Arrays.asList(pairs));
}
