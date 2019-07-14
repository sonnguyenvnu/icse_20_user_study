/** 
 * Requires the authorization request to be explicitly approved, including all individual scopes, and the user to be authenticated. A scope that was requested in the authorization request can be approved by sending a request parameter <code>scope.&lt;scopename&gt;</code> equal to "true" or "approved" (otherwise it will be assumed to have been denied). The  {@link ApprovalStore} will be updated to reflect the inputs.
 * @param authorizationRequest The authorization request.
 * @param userAuthentication the current user authentication
 * @return An approved request if all scopes have been approved by the current user.
 */
public AuthorizationRequest updateAfterApproval(AuthorizationRequest authorizationRequest,Authentication userAuthentication){
  Set<String> requestedScopes=authorizationRequest.getScope();
  Set<String> approvedScopes=new HashSet<String>();
  Set<Approval> approvals=new HashSet<Approval>();
  Date expiry=computeExpiry();
  Map<String,String> approvalParameters=authorizationRequest.getApprovalParameters();
  for (  String requestedScope : requestedScopes) {
    String approvalParameter=scopePrefix + requestedScope;
    String value=approvalParameters.get(approvalParameter);
    value=value == null ? "" : value.toLowerCase();
    if ("true".equals(value) || value.startsWith("approve")) {
      approvedScopes.add(requestedScope);
      approvals.add(new Approval(userAuthentication.getName(),authorizationRequest.getClientId(),requestedScope,expiry,ApprovalStatus.APPROVED));
    }
 else {
      approvals.add(new Approval(userAuthentication.getName(),authorizationRequest.getClientId(),requestedScope,expiry,ApprovalStatus.DENIED));
    }
  }
  approvalStore.addApprovals(approvals);
  boolean approved;
  authorizationRequest.setScope(approvedScopes);
  if (approvedScopes.isEmpty() && !requestedScopes.isEmpty()) {
    approved=false;
  }
 else {
    approved=true;
  }
  authorizationRequest.setApproved(approved);
  return authorizationRequest;
}
