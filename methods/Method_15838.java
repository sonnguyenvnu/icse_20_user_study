protected AccessTokenInfo getClientCredentialsToken(){
  return oAuth2UserTokenRepository.findByServerIdAndGrantType(serverConfig.getId(),GrantType.client_credentials).stream().findAny().orElse(null);
}
