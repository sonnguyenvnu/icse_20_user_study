protected HandlerCollection createHandler(Options options,AdminRequestHandler adminRequestHandler,StubRequestHandler stubRequestHandler){
  Notifier notifier=options.notifier();
  ServletContextHandler adminContext=addAdminContext(adminRequestHandler,notifier);
  ServletContextHandler mockServiceContext=addMockServiceContext(stubRequestHandler,options.filesRoot(),options.getAsynchronousResponseSettings(),options.getChunkedEncodingPolicy(),notifier);
  HandlerCollection handlers=new HandlerCollection();
  handlers.setHandlers(ArrayUtils.addAll(extensionHandlers(),adminContext));
  addGZipHandler(mockServiceContext,handlers);
  return handlers;
}
