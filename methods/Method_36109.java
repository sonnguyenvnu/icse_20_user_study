@Override public boolean isBrowserProxyRequest(){
  if (!JettyUtils.isJetty()) {
    return false;
  }
  if (request instanceof org.eclipse.jetty.server.Request) {
    org.eclipse.jetty.server.Request jettyRequest=(org.eclipse.jetty.server.Request)request;
    return JettyUtils.getUri(jettyRequest).isAbsolute();
  }
  return false;
}
