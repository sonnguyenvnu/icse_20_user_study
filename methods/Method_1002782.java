public static void setup(RequestContext ctx,MeterIdPrefixFunction meterIdPrefixFunction,boolean server){
  if (ctx.hasAttr(ATTR_REQUEST_METRICS_SET)) {
    return;
  }
  ctx.attr(ATTR_REQUEST_METRICS_SET).set(true);
  ctx.log().addListener(log -> onRequest(log,meterIdPrefixFunction,server),RequestLogAvailability.REQUEST_HEADERS,RequestLogAvailability.REQUEST_CONTENT);
}
