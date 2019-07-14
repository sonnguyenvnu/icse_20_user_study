@Override protected Class<?> getBeanClass(Element element){
  String type=element.getAttribute("type");
  if ("authorization_code".equals(type)) {
    return AuthorizationCodeResourceDetails.class;
  }
  if ("implicit".equals(type)) {
    return ImplicitResourceDetails.class;
  }
  if ("client_credentials".equals(type)) {
    return ClientCredentialsResourceDetails.class;
  }
  if ("password".equals(type)) {
    return ResourceOwnerPasswordResourceDetails.class;
  }
  return BaseOAuth2ProtectedResourceDetails.class;
}
