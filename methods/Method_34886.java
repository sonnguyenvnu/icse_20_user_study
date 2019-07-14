@Override public void connect(final HttpClientConnection conn,final HttpRoute route,final int connectTimeout,final HttpContext context) throws IOException {
  try {
    super.connect(conn,route,connectTimeout,context);
  }
 catch (  SSLProtocolException e) {
    Boolean enableSniValue=(Boolean)context.getAttribute(SniSSLConnectionSocketFactory.ENABLE_SNI);
    boolean enableSni=enableSniValue == null || enableSniValue;
    if (enableSni && e.getMessage() != null && e.getMessage().equals("handshake alert:  unrecognized_name")) {
      logger.warn("Server saw wrong SNI host, retrying without SNI");
      context.setAttribute(SniSSLConnectionSocketFactory.ENABLE_SNI,false);
      super.connect(conn,route,connectTimeout,context);
    }
 else {
      throw e;
    }
  }
}
