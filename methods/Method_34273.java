/** 
 * Checks whether the registered redirect uri query params key and values contains match the requested set The requested redirect uri query params are allowed to contain additional params which will be retained
 * @param registeredRedirectUriQueryParams
 * @param requestedRedirectUriQueryParams
 * @return whether the params match
 */
private boolean matchQueryParams(MultiValueMap<String,String> registeredRedirectUriQueryParams,MultiValueMap<String,String> requestedRedirectUriQueryParams){
  Iterator<String> iter=registeredRedirectUriQueryParams.keySet().iterator();
  while (iter.hasNext()) {
    String key=iter.next();
    List<String> registeredRedirectUriQueryParamsValues=registeredRedirectUriQueryParams.get(key);
    List<String> requestedRedirectUriQueryParamsValues=requestedRedirectUriQueryParams.get(key);
    if (!registeredRedirectUriQueryParamsValues.equals(requestedRedirectUriQueryParamsValues)) {
      return false;
    }
  }
  return true;
}
