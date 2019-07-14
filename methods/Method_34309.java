public static boolean hasAnyScopeMatching(Authentication authentication,String[] scopesRegex){
  if (authentication instanceof OAuth2Authentication) {
    OAuth2Request clientAuthentication=((OAuth2Authentication)authentication).getOAuth2Request();
    for (    String scope : clientAuthentication.getScope()) {
      for (      String regex : scopesRegex) {
        if (scope.matches(regex)) {
          return true;
        }
      }
    }
  }
  return false;
}
