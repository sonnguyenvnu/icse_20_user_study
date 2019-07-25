@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  final long longPollingTimeoutMillis=getLongPollingTimeoutMillis(req);
  final boolean isHealthy=isHealthy();
  final boolean useLongPolling;
  if (longPollingTimeoutMillis > 0) {
    final String expectedState=Ascii.toLowerCase(req.headers().get(HttpHeaderNames.IF_NONE_MATCH,""));
    if ("\"healthy\"".equals(expectedState) || "w/\"healthy\"".equals(expectedState)) {
      useLongPolling=isHealthy;
    }
 else     if ("\"unhealthy\"".equals(expectedState) || "w/\"unhealthy\"".equals(expectedState)) {
      useLongPolling=!isHealthy;
    }
 else {
      useLongPolling=false;
    }
  }
 else {
    useLongPolling=false;
  }
  final HttpMethod method=ctx.method();
  if (useLongPolling) {
switch (method) {
case HEAD:
case GET:
      break;
default :
    throw HttpStatusException.of(HttpStatus.METHOD_NOT_ALLOWED);
}
assert healthCheckerListener != null : "healthCheckerListener is null.";
assert pendingHealthyResponses != null : "pendingHealthyResponses is null.";
assert pendingUnhealthyResponses != null : "pendingUnhealthyResponses is null.";
synchronized (healthCheckerListener) {
  final boolean currentHealthiness=isHealthy();
  if (isHealthy == currentHealthiness) {
    final CompletableFuture<HttpResponse> future=new CompletableFuture<>();
    final ScheduledFuture<Boolean> timeoutFuture=ctx.eventLoop().schedule(() -> future.complete(HttpResponse.of(notModifiedHeaders)),longPollingTimeoutMillis,TimeUnit.MILLISECONDS);
    final PendingResponse pendingResponse=new PendingResponse(method,future,timeoutFuture);
    if (isHealthy) {
      pendingUnhealthyResponses.add(pendingResponse);
    }
 else {
      pendingHealthyResponses.add(pendingResponse);
    }
    updateRequestTimeout(ctx,longPollingTimeoutMillis);
    return HttpResponse.from(future);
  }
 else {
  }
}
}
switch (method) {
case HEAD:
case GET:
return newResponse(method,isHealthy);
case CONNECT:
case DELETE:
case OPTIONS:
case TRACE:
return HttpResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
}
assert method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH;
if (updateHandler == null) {
return HttpResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
}
return HttpResponse.from(updateHandler.handle(ctx,req).thenApply(updateResult -> {
if (updateResult != null) {
switch (updateResult) {
case HEALTHY:
serverHealth.setHealthy(true);
break;
case UNHEALTHY:
serverHealth.setHealthy(false);
break;
}
}
return HttpResponse.of(newResponse(method,isHealthy()));
}
));
}
