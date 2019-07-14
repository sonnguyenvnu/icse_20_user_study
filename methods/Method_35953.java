@SuppressWarnings("unchecked") private static Constructor<? extends JettyHttpServer> getServerConstructor(){
  try {
    Class<? extends JettyHttpServer> serverClass=(Class<? extends JettyHttpServer>)Class.forName("com.github.tomakehurst.wiremock.jetty94.Jetty94HttpServer");
    return safelyGetConstructor(serverClass,Options.class,AdminRequestHandler.class,StubRequestHandler.class);
  }
 catch (  ClassNotFoundException e) {
    try {
      Class<? extends JettyHttpServer> serverClass=(Class<? extends JettyHttpServer>)Class.forName("com.github.tomakehurst.wiremock.jetty92.Jetty92HttpServer");
      return safelyGetConstructor(serverClass,Options.class,AdminRequestHandler.class,StubRequestHandler.class);
    }
 catch (    ClassNotFoundException cnfe) {
      return safelyGetConstructor(JettyHttpServer.class,Options.class,AdminRequestHandler.class,StubRequestHandler.class);
    }
  }
}
