private ServletContextHandler addAdminContext(AdminRequestHandler adminRequestHandler,Notifier notifier){
  ServletContextHandler adminContext=new ServletContextHandler(jettyServer,ADMIN_CONTEXT_ROOT);
  adminContext.setInitParameter("org.eclipse.jetty.servlet.Default.maxCacheSize","0");
  String javaVendor=System.getProperty("java.vendor");
  if (javaVendor != null && javaVendor.toLowerCase().contains("android")) {
    adminContext.setInitParameter("org.eclipse.jetty.servlet.Default.resourceBase","assets");
  }
 else {
    adminContext.setInitParameter("org.eclipse.jetty.servlet.Default.resourceBase",Resources.getResource("assets").toString());
  }
  Resources.getResource("assets/swagger-ui/index.html");
  adminContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed","false");
  adminContext.addServlet(DefaultServlet.class,"/swagger-ui/*");
  adminContext.addServlet(DefaultServlet.class,"/recorder/*");
  ServletHolder servletHolder=adminContext.addServlet(WireMockHandlerDispatchingServlet.class,"/");
  servletHolder.setInitParameter(RequestHandler.HANDLER_CLASS_KEY,AdminRequestHandler.class.getName());
  adminContext.setAttribute(AdminRequestHandler.class.getName(),adminRequestHandler);
  adminContext.setAttribute(Notifier.KEY,notifier);
  adminContext.setAttribute(MultipartRequestConfigurer.KEY,buildMultipartRequestConfigurer());
  FilterHolder filterHolder=new FilterHolder(CrossOriginFilter.class);
  filterHolder.setInitParameters(ImmutableMap.of("chainPreflight","false","allowedOrigins","*","allowedHeaders","X-Requested-With,Content-Type,Accept,Origin,Authorization","allowedMethods","OPTIONS,GET,POST,PUT,PATCH,DELETE"));
  adminContext.addFilter(filterHolder,"/*",EnumSet.of(DispatcherType.REQUEST));
  return adminContext;
}
