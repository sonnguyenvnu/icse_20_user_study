/** 
 * ?????RESTful???.
 * @param packages RESTful??????
 * @param resourcePath ????
 * @param servletPath servlet??
 * @throws Exception ???????
 */
public void start(final String packages,final Optional<String> resourcePath,final Optional<String> servletPath) throws Exception {
  log.info("Elastic Job: Start RESTful server");
  HandlerList handlers=new HandlerList();
  if (resourcePath.isPresent()) {
    servletContextHandler.setBaseResource(Resource.newClassPathResource(resourcePath.get()));
    servletContextHandler.addServlet(new ServletHolder(DefaultServlet.class),"/*");
  }
  String servletPathStr=(servletPath.isPresent() ? servletPath.get() : "") + "/*";
  servletContextHandler.addServlet(getServletHolder(packages),servletPathStr);
  handlers.addHandler(servletContextHandler);
  server.setHandler(handlers);
  server.start();
}
