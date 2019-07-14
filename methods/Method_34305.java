public static boolean clientHasAnyRole(Authentication authentication,String... roles){
  if (authentication instanceof OAuth2Authentication) {
    OAuth2Request clientAuthentication=((OAuth2Authentication)authentication).getOAuth2Request();
    Collection<? extends GrantedAuthority> clientAuthorities=clientAuthentication.getAuthorities();
    if (clientAuthorities != null) {
      Set<String> roleSet=AuthorityUtils.authorityListToSet(clientAuthorities);
      for (      String role : roles) {
        if (roleSet.contains(role)) {
          return true;
        }
      }
    }
  }
  return false;
}
