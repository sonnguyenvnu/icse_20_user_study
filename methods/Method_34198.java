protected OAuth2Exception convertToExternal(JaxbOAuth2Exception jaxbOAuth2Exception){
  return OAuth2Exception.create(jaxbOAuth2Exception.getErrorCode(),jaxbOAuth2Exception.getDescription());
}
