/** 
 * It intercepts http requests and starts a span.
 * @since 0.23.0
 */
public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request,byte[] body,org.springframework.http.client.AsyncClientHttpRequestExecution execution) throws IOException {
  HttpRequestContext context=handler.handleStart(tracer.getCurrentSpan(),request,request);
  Scope ws=tracer.withSpan(handler.getSpanFromContext(context));
  try {
    ListenableFuture<ClientHttpResponse> result=execution.executeAsync(request,body);
    result.addCallback(new TracingAsyncClientHttpRequestInterceptor.TraceListenableFutureCallback(context,handler));
    return result;
  }
 catch (  IOException e) {
    handler.handleEnd(context,null,null,e);
    throw e;
  }
 finally {
    if (ws != null) {
      ws.close();
    }
  }
}
