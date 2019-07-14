/** 
 * Finds a valid header value that is valid for the OAuth header.
 * @param paramValues The possible values for the oauth header.
 * @return The selected value, or null if none were found.
 */
protected String findValidHeaderValue(Set<CharSequence> paramValues){
  String selectedValue=null;
  if (paramValues != null && !paramValues.isEmpty()) {
    CharSequence value=paramValues.iterator().next();
    if (!(value instanceof QueryParameterValue)) {
      selectedValue=value.toString();
    }
  }
  return selectedValue;
}
