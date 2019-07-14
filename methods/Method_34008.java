public static boolean consumerHasAnyRole(SecurityExpressionRoot root,String... roles){
  Authentication authentication=root.getAuthentication();
  if (authentication.getDetails() instanceof OAuthAuthenticationDetails) {
    OAuthAuthenticationDetails details=(OAuthAuthenticationDetails)authentication.getDetails();
    List<GrantedAuthority> consumerAuthorities=details.getConsumerDetails().getAuthorities();
    if (consumerAuthorities != null) {
      Set<String> roleSet=AuthorityUtils.authorityListToSet(consumerAuthorities);
      for (      String role : roles) {
        if (roleSet.contains(role)) {
          return true;
        }
      }
    }
  }
  return false;
}
