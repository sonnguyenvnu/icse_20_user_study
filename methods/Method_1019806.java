@Override public void process(HttpResponse httpResponse,HttpContext httpContext) throws HttpException, IOException {
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  SofaTracerSpan httpClientSpan=sofaTraceContext.getCurrentSpan();
  super.appendHttpClientResponseSpanTags(httpResponse,httpClientSpan);
  int statusCode=httpResponse.getStatusLine().getStatusCode();
  httpClientTracer.clientReceive(String.valueOf(statusCode));
}
