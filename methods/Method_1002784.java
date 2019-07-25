/** 
 * Serves the specified  {@link HttpRequest} by delegating it to the matching {@code 'doMETHOD()'} method.Override this method to perform an action for the requests of any HTTP methods: <pre> {@code}> public class MyHttpService extends AbstractHttpService  >     private final Map<HttpMethod, AtomicInteger> handledRequests = new ConcurrentHashMap<>(); > >     @Override >     public HttpResponse serve(ServiceRequestContext ctx, HttpRequest req) throws Exception { >         final HttpResponse res = super.serve(ctx, req); >         handledRequests.computeIfAbsent( >                 req.method(), method -> new AtomicInteger()).incrementAndGet(); >         return res; >     } > } }</pre>
 */
@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  try {
switch (req.method()) {
case OPTIONS:
      return doOptions(ctx,req);
case GET:
    return doGet(ctx,req);
case HEAD:
  return doHead(ctx,req);
case POST:
return doPost(ctx,req);
case PUT:
return doPut(ctx,req);
case PATCH:
return doPatch(ctx,req);
case DELETE:
return doDelete(ctx,req);
case TRACE:
return doTrace(ctx,req);
default :
return HttpResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
}
}
  finally {
final RequestLogBuilder logBuilder=ctx.logBuilder();
if (!logBuilder.isRequestContentDeferred()) {
logBuilder.requestContent(null,null);
}
logBuilder.serializationFormat(SerializationFormat.NONE);
}
}
