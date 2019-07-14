protected final void setHttpProxyPassword(String proxyPassword){
  httpConf=new MyHttpClientConfiguration(httpConf.getHttpProxyHost(),httpConf.getHttpProxyUser(),proxyPassword,httpConf.getHttpProxyPort(),httpConf.isHttpProxySocks(),httpConf.getHttpConnectionTimeout(),httpConf.getHttpReadTimeout(),httpConf.isPrettyDebugEnabled(),httpConf.isGZIPEnabled());
}
