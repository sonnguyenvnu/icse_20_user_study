/** 
 * Basic implementation just requires the authorization request to be explicitly approved and the user to be authenticated.
 * @param authorizationRequest The authorization request.
 * @param userAuthentication the current user authentication
 * @return Whether the specified request has been approved by the current user.
 */
public boolean isApproved(AuthorizationRequest authorizationRequest,Authentication userAuthentication){
  if (authorizationRequest.isApproved()) {
    return true;
  }
  return false;
}
