/** 
 * Extract the implied approvals from any tokens associated with the user and client id supplied.
 * @see org.springframework.security.oauth2.provider.approval.ApprovalStore#getApprovals(java.lang.String,java.lang.String)
 */
@Override public Collection<Approval> getApprovals(String userId,String clientId){
  Collection<Approval> result=new HashSet<Approval>();
  Collection<OAuth2AccessToken> tokens=store.findTokensByClientIdAndUserName(clientId,userId);
  for (  OAuth2AccessToken token : tokens) {
    OAuth2Authentication authentication=store.readAuthentication(token);
    if (authentication != null) {
      Date expiresAt=token.getExpiration();
      for (      String scope : token.getScope()) {
        result.add(new Approval(userId,clientId,scope,expiresAt,ApprovalStatus.APPROVED));
      }
    }
  }
  return result;
}
