@Override public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken,OAuth2Authentication oAuth2Authentication){
  Map<String,Object> info=new HashMap<>();
  info.put("message","hello world");
  ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);
  return oAuth2AccessToken;
}
