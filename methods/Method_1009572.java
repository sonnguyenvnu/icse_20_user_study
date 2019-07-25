synchronized public void init(InetAddress bindAddress,final Router router) throws InitializationException {
  try {
    if (log.isLoggable(Level.FINE))     log.fine("Setting executor service on servlet container adapter");
    getConfiguration().getServletContainerAdapter().setExecutorService(router.getConfiguration().getStreamServerExecutorService());
    if (log.isLoggable(Level.FINE))     log.fine("Adding connector: " + bindAddress + ":" + getConfiguration().getListenPort());
    hostAddress=bindAddress.getHostAddress();
    localPort=getConfiguration().getServletContainerAdapter().addConnector(hostAddress,getConfiguration().getListenPort());
    String contextPath=router.getConfiguration().getNamespace().getBasePath().getPath();
    getConfiguration().getServletContainerAdapter().registerServlet(contextPath,createServlet(router));
  }
 catch (  Exception ex) {
    throw new InitializationException("Could not initialize " + getClass().getSimpleName() + ": " + ex.toString(),ex);
  }
}
