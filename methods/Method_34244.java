private String getAutoApproveScopes(ClientDetails clientDetails){
  if (clientDetails.isAutoApprove("true")) {
    return "true";
  }
  Set<String> scopes=new HashSet<String>();
  for (  String scope : clientDetails.getScope()) {
    if (clientDetails.isAutoApprove(scope)) {
      scopes.add(scope);
    }
  }
  return StringUtils.collectionToCommaDelimitedString(scopes);
}
