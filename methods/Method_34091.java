private MultiValueMap<String,String> getParametersForTokenRequest(ClientCredentialsResourceDetails resource){
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("grant_type","client_credentials");
  if (resource.isScoped()) {
    StringBuilder builder=new StringBuilder();
    List<String> scope=resource.getScope();
    if (scope != null) {
      Iterator<String> scopeIt=scope.iterator();
      while (scopeIt.hasNext()) {
        builder.append(scopeIt.next());
        if (scopeIt.hasNext()) {
          builder.append(' ');
        }
      }
    }
    form.set("scope",builder.toString());
  }
  return form;
}
