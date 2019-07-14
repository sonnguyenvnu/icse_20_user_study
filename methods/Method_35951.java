@SuppressWarnings({"rawtypes","unchecked"}) private ServletContextHandler addMockServiceContext(StubRequestHandler stubRequestHandler,FileSource fileSource,AsynchronousResponseSettings asynchronousResponseSettings,Options.ChunkedEncodingPolicy chunkedEncodingPolicy,Notifier notifier){
  ServletContextHandler mockServiceContext=new ServletContextHandler(jettyServer,"/");
  mockServiceContext.setInitParameter("org.eclipse.jetty.servlet.Default.maxCacheSize","0");
  mockServiceContext.setInitParameter("org.eclipse.jetty.servlet.Default.resourceBase",fileSource.getPath());
  mockServiceContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed","false");
  mockServiceContext.addServlet(DefaultServlet.class,FILES_URL_MATCH);
  mockServiceContext.setAttribute(JettyFaultInjectorFactory.class.getName(),new JettyFaultInjectorFactory());
  mockServiceContext.setAttribute(StubRequestHandler.class.getName(),stubRequestHandler);
  mockServiceContext.setAttribute(Notifier.KEY,notifier);
  mockServiceContext.setAttribute(Options.ChunkedEncodingPolicy.class.getName(),chunkedEncodingPolicy);
  ServletHolder servletHolder=mockServiceContext.addServlet(WireMockHandlerDispatchingServlet.class,"/");
  servletHolder.setInitParameter(RequestHandler.HANDLER_CLASS_KEY,StubRequestHandler.class.getName());
  servletHolder.setInitParameter(FaultInjectorFactory.INJECTOR_CLASS_KEY,JettyFaultInjectorFactory.class.getName());
  servletHolder.setInitParameter(WireMockHandlerDispatchingServlet.SHOULD_FORWARD_TO_FILES_CONTEXT,"true");
  if (asynchronousResponseSettings.isEnabled()) {
    ScheduledExecutorService scheduledExecutorService=newScheduledThreadPool(asynchronousResponseSettings.getThreads());
    mockServiceContext.setAttribute(WireMockHandlerDispatchingServlet.ASYNCHRONOUS_RESPONSE_EXECUTOR,scheduledExecutorService);
  }
  mockServiceContext.setAttribute(MultipartRequestConfigurer.KEY,buildMultipartRequestConfigurer());
  MimeTypes mimeTypes=new MimeTypes();
  mimeTypes.addMimeMapping("json","application/json");
  mimeTypes.addMimeMapping("html","text/html");
  mimeTypes.addMimeMapping("xml","application/xml");
  mimeTypes.addMimeMapping("txt","text/plain");
  mockServiceContext.setMimeTypes(mimeTypes);
  mockServiceContext.setWelcomeFiles(new String[]{"index.json","index.html","index.xml","index.txt"});
  mockServiceContext.setErrorHandler(new NotFoundHandler());
  mockServiceContext.addFilter(ContentTypeSettingFilter.class,FILES_URL_MATCH,EnumSet.of(DispatcherType.FORWARD));
  mockServiceContext.addFilter(TrailingSlashFilter.class,FILES_URL_MATCH,EnumSet.allOf(DispatcherType.class));
  return mockServiceContext;
}
