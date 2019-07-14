/** 
 * Returns all values all of the values the given request parameter has.
 */
public String[] getParameterValues(final String paramName){
  if (requestParameters == null) {
    return null;
  }
  return requestParameters.get(paramName);
}
