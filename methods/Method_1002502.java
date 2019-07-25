@Override public void service(final HttpServletRequest req,final HttpServletResponse resp) throws ServletException, IOException {
  final AsyncContext ctx=req.startAsync(req,resp);
  ctx.setTimeout(_timeout);
  final WrappedAsyncContext wrappedCtx=new WrappedAsyncContext(ctx);
  final AsyncEventIOHandler ioHandler=new AsyncEventIOHandler(req.getInputStream(),resp.getOutputStream(),req.getRemoteAddr(),wrappedCtx,MAX_BUFFERED_CHUNKS,_logServletExceptions);
  final RequestContext requestContext=ServletHelper.readRequestContext(req);
  final StreamRequest streamRequest;
  try {
    streamRequest=ServletHelper.readFromServletRequest(req,ioHandler);
  }
 catch (  URISyntaxException e) {
    ServletHelper.writeToServletError(resp,RestStatus.BAD_REQUEST,e.toString());
    wrappedCtx.complete();
    return;
  }
  final AtomicBoolean startedResponding=new AtomicBoolean(false);
  ctx.addListener(new AsyncListener(){
    @Override public void onTimeout(    AsyncEvent event) throws IOException {
      LOG.error("Server timeout for request: " + formatURI(req.getRequestURI()));
      if (startedResponding.compareAndSet(false,true)) {
        LOG.info("Returning server timeout response");
        ServletHelper.writeToServletError(resp,RestStatus.INTERNAL_SERVER_ERROR,"Server timeout");
      }
 else {
        req.setAttribute(ASYNC_IOEXCEPTION,new ServletException("Server timeout"));
      }
      ioHandler.exitLoop();
      wrappedCtx.complete();
    }
    @Override public void onStartAsync(    AsyncEvent event) throws IOException {
    }
    @Override public void onError(    AsyncEvent event) throws IOException {
      LOG.error("Server error for request: " + formatURI(req.getRequestURI()));
      if (startedResponding.compareAndSet(false,true)) {
        LOG.info("Returning server error response");
        ServletHelper.writeToServletError(resp,RestStatus.INTERNAL_SERVER_ERROR,"Server error");
      }
 else {
        req.setAttribute(ASYNC_IOEXCEPTION,new ServletException("Server error"));
      }
      ioHandler.exitLoop();
      wrappedCtx.complete();
    }
    @Override public void onComplete(    AsyncEvent event) throws IOException {
      Object exception=req.getAttribute(ASYNC_IOEXCEPTION);
      if (exception != null) {
        throw new IOException((Throwable)exception);
      }
    }
  }
);
  final TransportCallback<StreamResponse> callback=new TransportCallback<StreamResponse>(){
    @Override public void onResponse(    final TransportResponse<StreamResponse> response){
      if (startedResponding.compareAndSet(false,true)) {
        ctx.start(new Runnable(){
          @Override public void run(){
            try {
              StreamResponse streamResponse=ServletHelper.writeResponseHeadersToServletResponse(response,resp);
              streamResponse.getEntityStream().setReader(ioHandler);
              ioHandler.loop();
            }
 catch (            Exception e) {
              req.setAttribute(ASYNC_IOEXCEPTION,e);
              wrappedCtx.complete();
            }
          }
        }
);
      }
 else {
        LOG.error("Dropped a response; this is mostly like because that AsyncContext timeout or error had already happened");
      }
    }
  }
;
  ctx.start(new Runnable(){
    @Override public void run(){
      try {
        getDispatcher().handleRequest(streamRequest,requestContext,callback);
        ioHandler.loop();
      }
 catch (      Exception e) {
        req.setAttribute(ASYNC_IOEXCEPTION,e);
        wrappedCtx.complete();
      }
    }
  }
);
}
