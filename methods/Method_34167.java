private void addUserDetailsService(DefaultTokenServices tokenServices,UserDetailsService userDetailsService){
  if (userDetailsService != null) {
    PreAuthenticatedAuthenticationProvider provider=new PreAuthenticatedAuthenticationProvider();
    provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(userDetailsService));
    tokenServices.setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider>asList(provider)));
  }
}
