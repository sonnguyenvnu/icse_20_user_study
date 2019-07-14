private MultiValueMap<String,String> getParametersForTokenRequest(ImplicitResourceDetails resource,AccessTokenRequest request){
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("response_type","token");
  form.set("client_id",resource.getClientId());
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
  for (  String key : request.keySet()) {
    form.put(key,request.get(key));
  }
  String redirectUri=resource.getRedirectUri(request);
  if (redirectUri == null) {
    throw new IllegalStateException("No redirect URI available in request");
  }
  form.set("redirect_uri",redirectUri);
  return form;
}
