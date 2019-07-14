private List<TokenGranter> getDefaultTokenGranters(){
  ClientDetailsService clientDetails=clientDetailsService();
  AuthorizationServerTokenServices tokenServices=tokenServices();
  AuthorizationCodeServices authorizationCodeServices=authorizationCodeServices();
  OAuth2RequestFactory requestFactory=requestFactory();
  List<TokenGranter> tokenGranters=new ArrayList<TokenGranter>();
  tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices,authorizationCodeServices,clientDetails,requestFactory));
  tokenGranters.add(new RefreshTokenGranter(tokenServices,clientDetails,requestFactory));
  ImplicitTokenGranter implicit=new ImplicitTokenGranter(tokenServices,clientDetails,requestFactory);
  tokenGranters.add(implicit);
  tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices,clientDetails,requestFactory));
  if (authenticationManager != null) {
    tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager,tokenServices,clientDetails,requestFactory));
  }
  return tokenGranters;
}
