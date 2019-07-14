private void remove(String token){
  if (approvalStore != null) {
    OAuth2Authentication auth=readAuthentication(token);
    String clientId=auth.getOAuth2Request().getClientId();
    Authentication user=auth.getUserAuthentication();
    if (user != null) {
      Collection<Approval> approvals=new ArrayList<Approval>();
      for (      String scope : auth.getOAuth2Request().getScope()) {
        approvals.add(new Approval(user.getName(),clientId,scope,new Date(),ApprovalStatus.APPROVED));
      }
      approvalStore.revokeApprovals(approvals);
    }
  }
}
