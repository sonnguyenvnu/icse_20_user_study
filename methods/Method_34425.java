private static String getApprovalKey(OAuth2Authentication authentication){
  String userName=authentication.getUserAuthentication() == null ? "" : authentication.getUserAuthentication().getName();
  return getApprovalKey(authentication.getOAuth2Request().getClientId(),userName);
}
