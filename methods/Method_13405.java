/** 
 * Get Parameters from the specified pairs of name-value. <p>
 * @param pairs The pairs of name-value
 * @return The query parameters
 */
public static MultiValueMap<String,String> getParameters(Iterable<String> pairs){
  MultiValueMap<String,String> parameters=new LinkedMultiValueMap<>();
  if (pairs != null) {
    for (    String pair : pairs) {
      String[] nameAndValue=delimitedListToStringArray(pair,EQUAL);
      String name=decode(nameAndValue[0]);
      String value=nameAndValue.length < 2 ? null : nameAndValue[1];
      value=decode(value);
      addParam(parameters,name,value);
    }
  }
  return parameters;
}
