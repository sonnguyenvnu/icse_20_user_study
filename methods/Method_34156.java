public AuthorizationServerTokenServices getTokenServices(){
  return ProxyCreator.getProxy(AuthorizationServerTokenServices.class,new ObjectFactory<AuthorizationServerTokenServices>(){
    @Override public AuthorizationServerTokenServices getObject() throws BeansException {
      return tokenServices();
    }
  }
);
}
