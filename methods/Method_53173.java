protected final void setHttpProxyHost(String proxyHost){
  httpConf=new MyHttpClientConfiguration(proxyHost,httpConf.getHttpProxyUser(),httpConf.getHttpProxyPassword(),httpConf.getHttpProxyPort(),httpConf.isHttpProxySocks(),httpConf.getHttpConnectionTimeout(),httpConf.getHttpReadTimeout(),httpConf.isPrettyDebugEnabled(),httpConf.isGZIPEnabled());
}
