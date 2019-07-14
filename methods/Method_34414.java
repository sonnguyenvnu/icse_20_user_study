@Override public OAuth2RefreshToken readRefreshToken(String tokenValue){
  OAuth2AccessToken encodedRefreshToken=convertAccessToken(tokenValue);
  OAuth2RefreshToken refreshToken=createRefreshToken(encodedRefreshToken);
  if (approvalStore != null) {
    OAuth2Authentication authentication=readAuthentication(tokenValue);
    if (authentication.getUserAuthentication() != null) {
      String userId=authentication.getUserAuthentication().getName();
      String clientId=authentication.getOAuth2Request().getClientId();
      Collection<Approval> approvals=approvalStore.getApprovals(userId,clientId);
      Collection<String> approvedScopes=new HashSet<String>();
      for (      Approval approval : approvals) {
        if (approval.isApproved()) {
          approvedScopes.add(approval.getScope());
        }
      }
      if (!approvedScopes.containsAll(authentication.getOAuth2Request().getScope())) {
        return null;
      }
    }
  }
  return refreshToken;
}
