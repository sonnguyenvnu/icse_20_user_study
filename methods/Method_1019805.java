@Override public void process(HttpResponse httpResponse,HttpContext httpContext) throws HttpException, IOException {
  SofaTracerSpan httpClientSpan=(SofaTracerSpan)httpContext.getAttribute(CURRENT_ASYNC_HTTP_SPAN_KEY);
  super.appendHttpClientResponseSpanTags(httpResponse,httpClientSpan);
  int statusCode=httpResponse.getStatusLine().getStatusCode();
  httpClientTracer.clientReceiveTagFinish(httpClientSpan,String.valueOf(statusCode));
}
