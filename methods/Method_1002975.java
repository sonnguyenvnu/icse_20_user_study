@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  final TraceContextOrSamplingFlags contextOrFlags=extractor.extract(req.headers());
  final Span span=contextOrFlags.context() != null ? tracer.joinSpan(contextOrFlags.context()) : tracer.nextSpan(contextOrFlags);
  if (span.isNoop()) {
    return delegate().serve(ctx,req);
  }
  final String method=ctx.method().name();
  span.kind(Kind.SERVER).name(method);
  ctx.log().addListener(log -> SpanContextUtil.startSpan(span,log),RequestLogAvailability.REQUEST_START);
  ctx.onChild(RequestContextCurrentTraceContext::copy);
  ctx.log().addListener(log -> {
    SpanTags.logWireReceive(span,log.requestFirstBytesTransferredTimeNanos(),log);
    if (log.isAvailable(RequestLogAvailability.RESPONSE_FIRST_BYTES_TRANSFERRED)) {
      SpanTags.logWireSend(span,log.responseFirstBytesTransferredTimeNanos(),log);
    }
    SpanContextUtil.closeSpan(span,log);
  }
,RequestLogAvailability.COMPLETE);
  try (SpanInScope ignored=tracer.withSpanInScope(span)){
    return delegate().serve(ctx,req);
  }
 }
