@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  final SamlServiceFunction func=serviceMap.get(req.path());
  if (func == null) {
    return HttpResponse.of(HttpStatus.BAD_REQUEST,MediaType.PLAIN_TEXT_UTF_8,DATA_INCORRECT_PATH);
  }
  final CompletionStage<AggregatedHttpRequest> f;
  if (portConfigHolder.isDone()) {
    f=req.aggregate();
  }
 else {
    f=portConfigHolder.future().thenCompose(unused -> req.aggregate());
  }
  return HttpResponse.from(f.handle((aggregatedReq,cause) -> {
    if (cause != null) {
      logger.warn("{} Failed to aggregate a SAML request.",ctx,cause);
      return HttpResponse.of(HttpStatus.BAD_REQUEST,MediaType.PLAIN_TEXT_UTF_8,DATA_AGGREGATION_FAILURE);
    }
    final SamlPortConfig portConfig=portConfigHolder.config().get();
    final boolean isTls=ctx.sessionProtocol().isTls();
    if (portConfig.scheme().isTls() != isTls) {
      if (isTls) {
        logger.warn("{} Received a SAML request via a TLS connection.",ctx);
        return HttpResponse.of(HttpStatus.BAD_REQUEST,MediaType.PLAIN_TEXT_UTF_8,DATA_NOT_CLEARTEXT);
      }
 else {
        logger.warn("{} Received a SAML request via a cleartext connection.",ctx);
        return HttpResponse.of(HttpStatus.BAD_REQUEST,MediaType.PLAIN_TEXT_UTF_8,DATA_NOT_TLS);
      }
    }
    final String defaultHostname=firstNonNull(sp.hostname(),ctx.virtualHost().defaultHostname());
    return func.serve(ctx,aggregatedReq,defaultHostname,portConfig);
  }
));
}
