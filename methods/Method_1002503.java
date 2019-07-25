@Override protected void service(final HttpServletRequest req,final HttpServletResponse resp) throws ServletException, IOException {
  RequestContext requestContext=ServletHelper.readRequestContext(req);
  RestRequest restRequest;
  try {
    restRequest=readFromServletRequest(req);
  }
 catch (  URISyntaxException e) {
    writeToServletError(resp,RestStatus.BAD_REQUEST,e.toString());
    return;
  }
  final AtomicReference<TransportResponse<RestResponse>> result=new AtomicReference<TransportResponse<RestResponse>>();
  final CountDownLatch latch=new CountDownLatch(1);
  TransportCallback<RestResponse> callback=new TransportCallback<RestResponse>(){
    @Override public void onResponse(    TransportResponse<RestResponse> response){
      result.set(response);
      latch.countDown();
    }
  }
;
  getDispatcher().handleRequest(restRequest,requestContext,callback);
  try {
    if (latch.await(_timeout,TimeUnit.MILLISECONDS)) {
      writeToServletResponse(result.get(),resp);
    }
 else {
      writeToServletError(resp,RestStatus.INTERNAL_SERVER_ERROR,"Server Timeout after " + _timeout + "ms.");
    }
  }
 catch (  InterruptedException e) {
    throw new ServletException("Interrupted!",e);
  }
}
