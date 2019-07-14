/** 
 * Attempt to match one of the registered URIs to the that of the requested one.
 * @param redirectUris the set of the registered URIs to try and find a match. This cannot be null or empty.
 * @param requestedRedirect the URI used as part of the request
 * @return redirect uri
 * @throws RedirectMismatchException if no match was found
 */
private String obtainMatchingRedirect(Set<String> redirectUris,String requestedRedirect){
  Assert.notEmpty(redirectUris,"Redirect URIs cannot be empty");
  if (redirectUris.size() == 1 && requestedRedirect == null) {
    return redirectUris.iterator().next();
  }
  for (  String redirectUri : redirectUris) {
    if (requestedRedirect != null && redirectMatches(requestedRedirect,redirectUri)) {
      UriComponentsBuilder redirectUriBuilder=UriComponentsBuilder.fromUriString(redirectUri);
      UriComponents requestedRedirectUri=UriComponentsBuilder.fromUriString(requestedRedirect).build();
      if (this.matchSubdomains) {
        redirectUriBuilder.host(requestedRedirectUri.getHost());
      }
      if (!this.matchPorts) {
        redirectUriBuilder.port(requestedRedirectUri.getPort());
      }
      redirectUriBuilder.replaceQuery(requestedRedirectUri.getQuery());
      redirectUriBuilder.fragment(null);
      return redirectUriBuilder.build().toUriString();
    }
  }
  throw new RedirectMismatchException("Invalid redirect: " + requestedRedirect + " does not match one of the registered values.");
}
