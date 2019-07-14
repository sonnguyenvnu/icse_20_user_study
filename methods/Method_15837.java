protected String getRealUrl(String url){
  if (url.startsWith("http")) {
    return url;
  }
  if (!serverConfig.getApiBaseUrl().endsWith("/") && !url.startsWith("/")) {
    return serverConfig.getApiBaseUrl().concat("/").concat(url);
  }
  return serverConfig.getApiBaseUrl() + url;
}
