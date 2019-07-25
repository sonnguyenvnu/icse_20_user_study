@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  final Optional<Adapter> coyoteAdapter=connector().map(c -> c.getProtocolHandler().getAdapter());
  if (!coyoteAdapter.isPresent()) {
    throw HttpStatusException.of(HttpStatus.SERVICE_UNAVAILABLE);
  }
  final HttpResponseWriter res=HttpResponse.streaming();
  req.aggregate().handle((aReq,cause) -> {
    try {
      if (cause != null) {
        logger.warn("{} Failed to aggregate a request:",ctx,cause);
        res.close(ResponseHeaders.of(HttpStatus.INTERNAL_SERVER_ERROR));
        return null;
      }
      final Request coyoteReq=convertRequest(ctx,aReq);
      if (coyoteReq == null) {
        if (res.tryWrite(INVALID_AUTHORITY_HEADERS)) {
          if (res.tryWrite(INVALID_AUTHORITY_DATA)) {
            res.close();
          }
        }
        return null;
      }
      final Response coyoteRes=new Response();
      coyoteReq.setResponse(coyoteRes);
      coyoteRes.setRequest(coyoteReq);
      final Queue<HttpData> data=new ArrayDeque<>();
      coyoteRes.setOutputBuffer((OutputBuffer)OUTPUT_BUFFER_CONSTRUCTOR.invoke(data));
      ctx.blockingTaskExecutor().execute(() -> {
        if (!res.isOpen()) {
          return;
        }
        try {
          coyoteAdapter.get().service(coyoteReq,coyoteRes);
          final HttpHeaders headers=convertResponse(coyoteRes);
          if (res.tryWrite(headers)) {
            for (; ; ) {
              final HttpData d=data.poll();
              if (d == null || !res.tryWrite(d)) {
                break;
              }
            }
          }
        }
 catch (        Throwable t) {
          logger.warn("{} Failed to produce a response:",ctx,t);
        }
 finally {
          res.close();
        }
      }
);
    }
 catch (    Throwable t) {
      logger.warn("{} Failed to invoke Tomcat:",ctx,t);
      res.close();
    }
    return null;
  }
);
  return res;
}
