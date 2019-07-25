@Override public CompletionStage<HealthCheckUpdateResult> handle(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  requireNonNull(req,"req");
switch (req.method()) {
case PUT:
case POST:
    return req.aggregate().thenApply(this::handlePut);
case PATCH:
  return req.aggregate().thenApply(this::handlePatch);
default :
throw HttpStatusException.of(HttpStatus.METHOD_NOT_ALLOWED);
}
}
