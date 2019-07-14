@SuppressWarnings("deprecation") private RequestConfig createRequestConfig(){
  return RequestConfig.custom().setRedirectsEnabled(false).setSocketTimeout(0).setStaleConnectionCheckEnabled(true).build();
}
