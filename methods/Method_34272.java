/** 
 * Whether the requested redirect URI "matches" the specified redirect URI. For a URL, this implementation tests if the user requested redirect starts with the registered redirect, so it would have the same host and root path if it is an HTTP URL. The port, userinfo, query params also matched. Request redirect uri path can include additional parameters which are ignored for the match <p> For other (non-URL) cases, such as for some implicit clients, the redirect_uri must be an exact match.
 * @param requestedRedirect The requested redirect URI.
 * @param redirectUri The registered redirect URI.
 * @return Whether the requested redirect URI "matches" the specified redirect URI.
 */
protected boolean redirectMatches(String requestedRedirect,String redirectUri){
  UriComponents requestedRedirectUri=UriComponentsBuilder.fromUriString(requestedRedirect).build();
  UriComponents registeredRedirectUri=UriComponentsBuilder.fromUriString(redirectUri).build();
  boolean schemeMatch=isEqual(registeredRedirectUri.getScheme(),requestedRedirectUri.getScheme());
  boolean userInfoMatch=isEqual(registeredRedirectUri.getUserInfo(),requestedRedirectUri.getUserInfo());
  boolean hostMatch=hostMatches(registeredRedirectUri.getHost(),requestedRedirectUri.getHost());
  boolean portMatch=matchPorts ? registeredRedirectUri.getPort() == requestedRedirectUri.getPort() : true;
  boolean pathMatch=isEqual(registeredRedirectUri.getPath(),StringUtils.cleanPath(requestedRedirectUri.getPath()));
  boolean queryParamMatch=matchQueryParams(registeredRedirectUri.getQueryParams(),requestedRedirectUri.getQueryParams());
  return schemeMatch && userInfoMatch && hostMatch && portMatch && pathMatch && queryParamMatch;
}
