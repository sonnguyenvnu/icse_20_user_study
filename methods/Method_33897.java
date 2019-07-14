/** 
 * Allows automatic approval for a white list of clients in the implicit grant case.
 * @param authorizationRequest The authorization request.
 * @param userAuthentication the current user authentication
 * @return An updated request if it has already been approved by the current user.
 */
@Override public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest,Authentication userAuthentication){
  boolean approved=false;
  if (useApprovalStore) {
    authorizationRequest=super.checkForPreApproval(authorizationRequest,userAuthentication);
    approved=authorizationRequest.isApproved();
  }
 else {
    if (clientDetailsService != null) {
      Collection<String> requestedScopes=authorizationRequest.getScope();
      try {
        ClientDetails client=clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
        for (        String scope : requestedScopes) {
          if (client.isAutoApprove(scope)) {
            approved=true;
            break;
          }
        }
      }
 catch (      ClientRegistrationException e) {
      }
    }
  }
  authorizationRequest.setApproved(approved);
  return authorizationRequest;
}
