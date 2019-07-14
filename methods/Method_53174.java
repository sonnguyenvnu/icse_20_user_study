protected final void setHttpProxyUser(String proxyUser){
  httpConf=new MyHttpClientConfiguration(httpConf.getHttpProxyHost(),proxyUser,httpConf.getHttpProxyPassword(),httpConf.getHttpProxyPort(),httpConf.isHttpProxySocks(),httpConf.getHttpConnectionTimeout(),httpConf.getHttpReadTimeout(),httpConf.isPrettyDebugEnabled(),httpConf.isGZIPEnabled());
}
