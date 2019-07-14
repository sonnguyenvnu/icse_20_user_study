public void authenticateTokenRequest(OAuth2ProtectedResourceDetails resource,MultiValueMap<String,String> form,HttpHeaders headers){
  if (resource.isAuthenticationRequired()) {
    AuthenticationScheme scheme=AuthenticationScheme.header;
    if (resource.getClientAuthenticationScheme() != null) {
      scheme=resource.getClientAuthenticationScheme();
    }
    try {
      String clientSecret=resource.getClientSecret();
      clientSecret=clientSecret == null ? "" : clientSecret;
switch (scheme) {
case header:
        form.remove("client_secret");
      headers.add("Authorization",String.format("Basic %s",new String(Base64.encode(String.format("%s:%s",resource.getClientId(),clientSecret).getBytes("UTF-8")),"UTF-8")));
    break;
case form:
case query:
  form.set("client_id",resource.getClientId());
if (StringUtils.hasText(clientSecret)) {
  form.set("client_secret",clientSecret);
}
break;
default :
throw new IllegalStateException("Default authentication handler doesn't know how to handle scheme: " + scheme);
}
}
 catch (UnsupportedEncodingException e) {
throw new IllegalStateException(e);
}
}
}
