public static boolean isOAuthUserAuth(Authentication authentication){
  if (authentication instanceof OAuth2Authentication) {
    return authentication.isAuthenticated() && !((OAuth2Authentication)authentication).isClientOnly();
  }
  return false;
}
