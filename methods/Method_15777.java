public DefaultOAuth2Granter addImplicitSupport(ImplicitGranter implicitGranter){
  return addGranter(GrantType.implicit,ImplicitRequest.class,implicitGranter::requestToken);
}
