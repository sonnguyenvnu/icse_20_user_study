@PostConstruct public void init(){
  this.secretKey=jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
  this.tokenValidityInMilliseconds=1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
  this.tokenValidityInMillisecondsForRememberMe=1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
}
