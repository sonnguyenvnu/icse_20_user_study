protected final void setPrettyDebugEnabled(boolean prettyDebug){
  httpConf=new MyHttpClientConfiguration(httpConf.getHttpProxyHost(),httpConf.getHttpProxyUser(),httpConf.getHttpProxyPassword(),httpConf.getHttpProxyPort(),httpConf.isHttpProxySocks(),httpConf.getHttpConnectionTimeout(),httpConf.getHttpReadTimeout(),prettyDebug,httpConf.isGZIPEnabled());
}
