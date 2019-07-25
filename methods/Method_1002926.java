@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req){
  final ArmeriaConnector connector=this.connector;
  assert connector != null;
  final HttpResponseWriter res=HttpResponse.streaming();
  req.aggregate().handle((aReq,cause) -> {
    if (cause != null) {
      logger.warn("{} Failed to aggregate a request:",ctx,cause);
      res.close(ResponseHeaders.of(HttpStatus.INTERNAL_SERVER_ERROR));
      return null;
    }
    boolean success=false;
    try {
      final ArmeriaHttpTransport transport=new ArmeriaHttpTransport(req.method());
      final HttpChannel httpChannel=new HttpChannel(connector,connector.getHttpConfiguration(),new ArmeriaEndPoint(hostname,connector.getScheduler(),ctx.localAddress(),ctx.remoteAddress()),transport);
      fillRequest(ctx,aReq,httpChannel.getRequest());
      ctx.blockingTaskExecutor().execute(() -> invoke(ctx,res,transport,httpChannel));
      success=true;
      return null;
    }
  finally {
      if (!success) {
        res.close();
      }
    }
  }
).exceptionally(CompletionActions::log);
  return res;
}
