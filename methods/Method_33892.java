private void checkResourceOwner(String user,Principal principal){
  if (principal instanceof OAuth2Authentication) {
    OAuth2Authentication authentication=(OAuth2Authentication)principal;
    if (!authentication.isClientOnly() && !user.equals(principal.getName())) {
      throw new AccessDeniedException(String.format("User '%s' cannot obtain tokens for user '%s'",principal.getName(),user));
    }
  }
}
