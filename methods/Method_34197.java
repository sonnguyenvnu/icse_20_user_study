protected JaxbOAuth2Exception convertToInternal(OAuth2Exception exception){
  JaxbOAuth2Exception result=new JaxbOAuth2Exception();
  result.setDescription(exception.getMessage());
  result.setErrorCode(exception.getOAuth2ErrorCode());
  return result;
}
