@Bean @Profile("insecure") public SecurityWebFilterChain securityWebFilterChainPermitAll(ServerHttpSecurity http){
  return http.authorizeExchange().anyExchange().permitAll().and().csrf().disable().build();
}
