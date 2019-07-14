protected boolean isProxyConfigured(){
  return CONF.getHttpProxyHost() != null && !CONF.getHttpProxyHost().equals("");
}
