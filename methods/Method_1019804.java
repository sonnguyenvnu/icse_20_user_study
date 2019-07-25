@Override public void process(HttpRequest httpRequest,HttpContext httpContext) throws HttpException, IOException {
  RequestLine requestLine=httpRequest.getRequestLine();
  String methodName=requestLine.getMethod();
  SofaTracerSpan httpClientSpan=httpClientTracer.clientSend(methodName);
  super.appendHttpClientRequestSpanTags(httpRequest,httpClientSpan);
  httpContext.setAttribute(CURRENT_ASYNC_HTTP_SPAN_KEY,httpClientSpan);
  SofaTraceContext sofaTraceContext=SofaTraceContextHolder.getSofaTraceContext();
  if (httpClientSpan.getParentSofaTracerSpan() != null) {
    sofaTraceContext.push(httpClientSpan.getParentSofaTracerSpan());
  }
 else {
    sofaTraceContext.pop();
  }
}
