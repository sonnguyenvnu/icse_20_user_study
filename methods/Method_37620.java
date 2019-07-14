/** 
 * Returns the names of the parameters contained in this request.
 */
public Set<String> getParameterNames(){
  if (requestParameters == null) {
    return Collections.emptySet();
  }
  return requestParameters.keySet();
}
