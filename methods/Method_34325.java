/** 
 * Update the scope and create a new request. All the other properties are the same (including the request parameters).
 * @param scope the new scope
 * @return a new request with the narrowed scope
 */
public OAuth2Request narrowScope(Set<String> scope){
  OAuth2Request request=new OAuth2Request(getRequestParameters(),getClientId(),authorities,approved,scope,resourceIds,redirectUri,responseTypes,extensions);
  request.refresh=this.refresh;
  return request;
}
