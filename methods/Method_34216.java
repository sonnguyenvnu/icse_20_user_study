/** 
 * Revoke all tokens that match the client and user in the approvals supplied.
 * @see org.springframework.security.oauth2.provider.approval.ApprovalStore#revokeApprovals(java.util.Collection)
 */
@Override public boolean revokeApprovals(Collection<Approval> approvals){
  boolean success=true;
  for (  Approval approval : approvals) {
    Collection<OAuth2AccessToken> tokens=store.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId());
    for (    OAuth2AccessToken token : tokens) {
      OAuth2Authentication authentication=store.readAuthentication(token);
      if (authentication != null && approval.getClientId().equals(authentication.getOAuth2Request().getClientId())) {
        store.removeAccessToken(token);
      }
    }
  }
  return success;
}
