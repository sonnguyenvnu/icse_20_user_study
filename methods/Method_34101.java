protected UserApprovalRequiredException getUserApprovalSignal(AuthorizationCodeResourceDetails resource,AccessTokenRequest request){
  String message=String.format("Do you approve the client '%s' to access your resources with scope=%s",resource.getClientId(),resource.getScope());
  return new UserApprovalRequiredException(resource.getUserAuthorizationUri(),Collections.singletonMap(OAuth2Utils.USER_OAUTH_APPROVAL,message),resource.getClientId(),resource.getScope());
}
