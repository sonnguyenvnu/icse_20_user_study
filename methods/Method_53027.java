HttpURLConnection getConnection(String url) throws IOException {
  HttpURLConnection con;
  if (isProxyConfigured()) {
    if (CONF.getHttpProxyUser() != null && !CONF.getHttpProxyUser().equals("")) {
      if (logger.isDebugEnabled()) {
        logger.debug("Proxy AuthUser: " + CONF.getHttpProxyUser());
        logger.debug("Proxy AuthPassword: " + CONF.getHttpProxyPassword().replaceAll(".","*"));
      }
      Authenticator.setDefault(new Authenticator(){
        @Override protected PasswordAuthentication getPasswordAuthentication(){
          if (getRequestorType().equals(RequestorType.PROXY)) {
            return new PasswordAuthentication(CONF.getHttpProxyUser(),CONF.getHttpProxyPassword().toCharArray());
          }
 else {
            return null;
          }
        }
      }
);
    }
    final Proxy proxy=new Proxy(CONF.isHttpProxySocks() ? Proxy.Type.SOCKS : Proxy.Type.HTTP,InetSocketAddress.createUnresolved(CONF.getHttpProxyHost(),CONF.getHttpProxyPort()));
    if (logger.isDebugEnabled()) {
      logger.debug("Opening proxied connection(" + CONF.getHttpProxyHost() + ":" + CONF.getHttpProxyPort() + ")");
    }
    con=(HttpURLConnection)new URL(url).openConnection(proxy);
  }
 else {
    con=(HttpURLConnection)new URL(url).openConnection();
  }
  if (CONF.getHttpConnectionTimeout() > 0) {
    con.setConnectTimeout(CONF.getHttpConnectionTimeout());
  }
  if (CONF.getHttpReadTimeout() > 0) {
    con.setReadTimeout(CONF.getHttpReadTimeout());
  }
  con.setInstanceFollowRedirects(false);
  return con;
}
