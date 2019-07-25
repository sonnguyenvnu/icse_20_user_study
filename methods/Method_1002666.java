@Override public O execute(ClientRequestContext ctx,I req) throws Exception {
  if (sampler.isSampled()) {
    ctx.log().addListener(log -> logRequest(logger,log,requestLogLevel,requestHeadersSanitizer,requestContentSanitizer,requestTrailersSanitizer),RequestLogAvailability.REQUEST_END);
    ctx.log().addListener(log -> logResponse(logger,log,requestLogLevel,requestHeadersSanitizer,requestContentSanitizer,requestHeadersSanitizer,successfulResponseLogLevel,failedResponseLogLevel,responseHeadersSanitizer,responseContentSanitizer,responseTrailersSanitizer,responseCauseSanitizer),RequestLogAvailability.COMPLETE);
  }
  return delegate().execute(ctx,req);
}
