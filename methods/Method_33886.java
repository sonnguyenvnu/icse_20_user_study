@RequestMapping("/oauth/confirm_access") public ModelAndView getAccessConfirmation(Map<String,Object> model,Principal principal) throws Exception {
  AuthorizationRequest clientAuth=(AuthorizationRequest)model.remove("authorizationRequest");
  ClientDetails client=clientDetailsService.loadClientByClientId(clientAuth.getClientId());
  model.put("auth_request",clientAuth);
  model.put("client",client);
  Map<String,String> scopes=new LinkedHashMap<String,String>();
  for (  String scope : clientAuth.getScope()) {
    scopes.put(OAuth2Utils.SCOPE_PREFIX + scope,"false");
  }
  for (  Approval approval : approvalStore.getApprovals(principal.getName(),client.getClientId())) {
    if (clientAuth.getScope().contains(approval.getScope())) {
      scopes.put(OAuth2Utils.SCOPE_PREFIX + approval.getScope(),approval.getStatus() == ApprovalStatus.APPROVED ? "true" : "false");
    }
  }
  model.put("scopes",scopes);
  return new ModelAndView("access_confirmation",model);
}
