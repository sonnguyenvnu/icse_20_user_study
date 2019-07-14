@Override public AuthorizationRequest updateAfterApproval(AuthorizationRequest authorizationRequest,Authentication userAuthentication){
  Map<String,String> approvalParameters=authorizationRequest.getApprovalParameters();
  String flag=approvalParameters.get(approvalParameter);
  boolean approved=flag != null && flag.toLowerCase().equals("true");
  authorizationRequest.setApproved(approved);
  return authorizationRequest;
}
