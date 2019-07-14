protected final void setHttpReadTimeout(int readTimeout){
  httpConf=new MyHttpClientConfiguration(httpConf.getHttpProxyHost(),httpConf.getHttpProxyUser(),httpConf.getHttpProxyPassword(),httpConf.getHttpProxyPort(),httpConf.isHttpProxySocks(),httpConf.getHttpConnectionTimeout(),readTimeout,httpConf.isPrettyDebugEnabled(),httpConf.isGZIPEnabled());
}
