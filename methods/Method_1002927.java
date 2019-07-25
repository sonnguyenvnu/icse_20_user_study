private void invoke(ServiceRequestContext ctx,HttpResponseWriter res,ArmeriaHttpTransport transport,HttpChannel httpChannel){
  final Queue<HttpData> out=transport.out;
  try {
    server.handle(httpChannel);
    httpChannel.getResponse().getHttpOutput().flush();
    final Throwable cause=transport.cause;
    if (cause != null) {
      throw cause;
    }
    final HttpHeaders headers=toResponseHeaders(transport);
    if (res.tryWrite(headers)) {
      for (; ; ) {
        final HttpData data=out.poll();
        if (data == null || !res.tryWrite(data)) {
          break;
        }
      }
    }
  }
 catch (  Throwable t) {
    logger.warn("{} Failed to produce a response:",ctx,t);
  }
 finally {
    res.close();
  }
}
