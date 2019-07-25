protected WeixinResponse otherwise(WeixinRequest weixinRequest) throws HttpResponseException {
  throw new HttpResponseException(HttpResponseException.HttpResponseStatus.METHOD_NOT_ALLOWED);
}
