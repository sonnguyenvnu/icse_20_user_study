@Override @SuppressWarnings("all") public <T>List<T> convertList(OAuth2Response response,Class<T> type){
  String json=response.asString();
  Object data=tryConvertToObject(json,type,response);
  if (null == data)   return null;
  if (data instanceof List) {
    return ((List)data);
  }
  if (data instanceof ResponseMessage) {
    throw new OAuth2RequestException(((ResponseMessage)data).getMessage(),ErrorType.SERVICE_ERROR,response);
  }
  throw new OAuth2RequestException(ErrorType.PARSE_RESPONSE_ERROR,response);
}
