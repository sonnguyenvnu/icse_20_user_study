protected RequestCallback getRequestCallback(OAuth2ProtectedResourceDetails resource,MultiValueMap<String,String> form,HttpHeaders headers){
  return new OAuth2AuthTokenCallback(form,headers);
}
