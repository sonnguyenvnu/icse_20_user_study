@Override public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,OAuth2Authentication authentication){
  final Map<String,Object> additionalInfo=new HashMap<>();
  additionalInfo.put("organization",authentication.getName() + randomAlphabetic(4));
  ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);
  return accessToken;
}
