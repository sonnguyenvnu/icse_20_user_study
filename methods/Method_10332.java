private HttpEntity createFormEntity(){
  try {
    return new UrlEncodedFormEntity(getParamsList(),contentEncoding);
  }
 catch (  UnsupportedEncodingException e) {
    AsyncHttpClient.log.e(LOG_TAG,"createFormEntity failed",e);
    return null;
  }
}
