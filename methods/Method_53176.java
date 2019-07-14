protected final void setHttpProxyPort(int proxyPort){
  httpConf=new MyHttpClientConfiguration(httpConf.getHttpProxyHost(),httpConf.getHttpProxyUser(),httpConf.getHttpProxyPassword(),proxyPort,httpConf.isHttpProxySocks(),httpConf.getHttpConnectionTimeout(),httpConf.getHttpReadTimeout(),httpConf.isPrettyDebugEnabled(),httpConf.isGZIPEnabled());
}
