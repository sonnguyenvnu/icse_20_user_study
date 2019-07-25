@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  if (config.isEnabled()) {
    if (isCorsPreflightRequest(req)) {
      return handleCorsPreflight(ctx,req);
    }
    if (config.isShortCircuit() && config.getPolicy(req.headers().get(HttpHeaderNames.ORIGIN),ctx.routingContext()) == null) {
      return forbidden();
    }
  }
  return new FilteredHttpResponse(delegate().serve(ctx,req)){
    @Override protected HttpObject filter(    HttpObject obj){
      if (!(obj instanceof ResponseHeaders)) {
        return obj;
      }
      final ResponseHeaders headers=(ResponseHeaders)obj;
      final HttpStatus status=headers.status();
      if (status.codeClass() == HttpStatusClass.INFORMATIONAL) {
        return headers;
      }
      final ResponseHeadersBuilder builder=headers.toBuilder();
      setCorsResponseHeaders(ctx,req,builder);
      return builder.build();
    }
  }
;
}
