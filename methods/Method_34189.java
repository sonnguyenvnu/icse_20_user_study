public OAuth2Exception read(Class<? extends OAuth2Exception> clazz,HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
  MultiValueMap<String,String> data=delegateMessageConverter.read(null,inputMessage);
  Map<String,String> flattenedData=data.toSingleValueMap();
  return OAuth2Exception.valueOf(flattenedData);
}
