public static boolean hasAnyScope(Authentication authentication,String[] scopes){
  if (authentication instanceof OAuth2Authentication) {
    OAuth2Request clientAuthentication=((OAuth2Authentication)authentication).getOAuth2Request();
    Collection<String> assigned=clientAuthentication.getScope();
    if (assigned != null) {
      for (      String scope : scopes) {
        if (assigned.contains(scope)) {
          return true;
        }
      }
    }
  }
  return false;
}
