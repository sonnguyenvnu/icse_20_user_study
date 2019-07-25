/** 
 * Judge the turning mode. True means turn on, False is turn off. If not present, Not supported. Default implementation is check content is on/off for PUT method
 * @param req HttpRequest
 */
protected CompletionStage<Optional<Boolean>> mode(@SuppressWarnings("unused") ServiceRequestContext ctx,HttpRequest req){
  return req.aggregate().thenApply(AggregatedHttpRequest::content).thenApply(HttpData::toStringAscii).thenApply(content -> {
switch (Ascii.toUpperCase(content)) {
case "ON":
      return Optional.of(true);
case "OFF":
    return Optional.of(false);
default :
  return Optional.empty();
}
}
);
}
