public static boolean isOAuthConsumerAuth(SecurityExpressionRoot root){
  Authentication authentication=root.getAuthentication();
  if (authentication.getDetails() instanceof OAuthAuthenticationDetails) {
    return true;
  }
  return false;
}
