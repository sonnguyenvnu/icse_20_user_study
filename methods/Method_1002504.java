@Override protected void service(final HttpServletRequest req,final HttpServletResponse resp) throws ServletException, IOException {
  final SyncIOHandler ioHandler=new SyncIOHandler(req.getInputStream(),resp.getOutputStream(),req.getRemoteAddr(),2,_ioHandlerTimeout,_logServletExceptions);
  RequestContext requestContext=ServletHelper.readRequestContext(req);
  StreamRequest streamRequest;
  try {
    streamRequest=ServletHelper.readFromServletRequest(req,ioHandler);
  }
 catch (  URISyntaxException e) {
    ServletHelper.writeToServletError(resp,RestStatus.BAD_REQUEST,e.toString());
    return;
  }
  TransportCallback<StreamResponse> callback=new TransportCallback<StreamResponse>(){
    @Override public void onResponse(    TransportResponse<StreamResponse> response){
      StreamResponse streamResponse=ServletHelper.writeResponseHeadersToServletResponse(response,resp);
      streamResponse.getEntityStream().setReader(ioHandler);
    }
  }
;
  getDispatcher().handleRequest(streamRequest,requestContext,callback);
  ioHandler.loop();
}
