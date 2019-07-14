protected final void setHttpProxySocks(boolean isSocksProxy){
  httpConf=new MyHttpClientConfiguration(httpConf.getHttpProxyHost(),httpConf.getHttpProxyUser(),httpConf.getHttpProxyPassword(),httpConf.getHttpProxyPort(),isSocksProxy,httpConf.getHttpConnectionTimeout(),httpConf.getHttpReadTimeout(),httpConf.isPrettyDebugEnabled(),httpConf.isGZIPEnabled());
}
