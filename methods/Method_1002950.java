private static HttpResponse fail(ServiceRequestContext ctx,Throwable cause){
  logger.warn("{} Cannot handle a logout request",ctx,cause);
  return HttpResponse.of(HttpStatus.SERVICE_UNAVAILABLE);
}
