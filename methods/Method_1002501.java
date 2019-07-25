@Override public void service(final HttpServletRequest req,final HttpServletResponse resp) throws ServletException, IOException {
  RequestContext requestContext=ServletHelper.readRequestContext(req);
  RestRequest restRequest;
  try {
    restRequest=readFromServletRequest(req);
  }
 catch (  URISyntaxException e) {
    writeToServletError(resp,RestStatus.BAD_REQUEST,e.toString());
    return;
  }
  final AsyncContext ctx=req.startAsync(req,resp);
  ctx.setTimeout(_timeout);
  ctx.addListener(new AsyncListener(){
    @Override public void onTimeout(    AsyncEvent event) throws IOException {
      AsyncContext ctx=event.getAsyncContext();
      writeToServletError((HttpServletResponse)ctx.getResponse(),RestStatus.INTERNAL_SERVER_ERROR,"Server Timeout");
      ctx.complete();
    }
    @Override public void onStartAsync(    AsyncEvent event) throws IOException {
    }
    @Override public void onError(    AsyncEvent event) throws IOException {
      writeToServletError((HttpServletResponse)event.getSuppliedResponse(),RestStatus.INTERNAL_SERVER_ERROR,"Server Error");
      ctx.complete();
    }
    @Override public void onComplete(    AsyncEvent event) throws IOException {
      Object exception=req.getAttribute(TRANSPORT_CALLBACK_IOEXCEPTION);
      if (exception != null)       throw new IOException((IOException)exception);
    }
  }
);
  TransportCallback<RestResponse> callback=new TransportCallback<RestResponse>(){
    @Override public void onResponse(    final TransportResponse<RestResponse> response){
      ctx.start(new Runnable(){
        @Override public void run(){
          try {
            writeToServletResponse(response,(HttpServletResponse)ctx.getResponse());
          }
 catch (          IOException e) {
            req.setAttribute(TRANSPORT_CALLBACK_IOEXCEPTION,e);
          }
 finally {
            ctx.complete();
          }
        }
      }
);
    }
  }
;
  getDispatcher().handleRequest(restRequest,requestContext,callback);
}
