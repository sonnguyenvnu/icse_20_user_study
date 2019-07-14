public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest,Authentication userAuthentication){
  String clientId=authorizationRequest.getClientId();
  Collection<String> requestedScopes=authorizationRequest.getScope();
  Set<String> approvedScopes=new HashSet<String>();
  Set<String> validUserApprovedScopes=new HashSet<String>();
  if (clientDetailsService != null) {
    try {
      ClientDetails client=clientDetailsService.loadClientByClientId(clientId);
      for (      String scope : requestedScopes) {
        if (client.isAutoApprove(scope)) {
          approvedScopes.add(scope);
        }
      }
      if (approvedScopes.containsAll(requestedScopes)) {
        Set<Approval> approvals=new HashSet<Approval>();
        Date expiry=computeExpiry();
        for (        String approvedScope : approvedScopes) {
          approvals.add(new Approval(userAuthentication.getName(),authorizationRequest.getClientId(),approvedScope,expiry,ApprovalStatus.APPROVED));
        }
        approvalStore.addApprovals(approvals);
        authorizationRequest.setApproved(true);
        return authorizationRequest;
      }
    }
 catch (    ClientRegistrationException e) {
      logger.warn("Client registration problem prevent autoapproval check for client=" + clientId);
    }
  }
  if (logger.isDebugEnabled()) {
    StringBuilder builder=new StringBuilder("Looking up user approved authorizations for ");
    builder.append("client_id=" + clientId);
    builder.append(" and username=" + userAuthentication.getName());
    logger.debug(builder.toString());
  }
  Collection<Approval> userApprovals=approvalStore.getApprovals(userAuthentication.getName(),clientId);
  Date today=new Date();
  for (  Approval approval : userApprovals) {
    if (approval.getExpiresAt().after(today)) {
      if (approval.getStatus() == ApprovalStatus.APPROVED) {
        validUserApprovedScopes.add(approval.getScope());
        approvedScopes.add(approval.getScope());
      }
    }
  }
  if (logger.isDebugEnabled()) {
    logger.debug("Valid user approved/denied scopes are " + validUserApprovedScopes);
  }
  if (validUserApprovedScopes.containsAll(requestedScopes)) {
    approvedScopes.retainAll(requestedScopes);
    authorizationRequest.setScope(approvedScopes);
    authorizationRequest.setApproved(true);
  }
  return authorizationRequest;
}
