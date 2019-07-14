@Bean @Profile("secure") public SecurityWebFilterChain securityWebFilterChainSecure(ServerHttpSecurity http){
  return http.authorizeExchange().pathMatchers(this.adminServer.path("/assets/**")).permitAll().pathMatchers(this.adminServer.path("/login")).permitAll().anyExchange().authenticated().and().formLogin().loginPage(this.adminServer.path("/login")).and().logout().logoutUrl(this.adminServer.path("/logout")).and().httpBasic().and().csrf().disable().build();
}
