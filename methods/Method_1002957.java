Mono<Void> handle(ServiceRequestContext ctx,HttpRequest req,CompletableFuture<HttpResponse> future,@Nullable String serverHeader){
  final ArmeriaServerHttpRequest convertedRequest;
  try {
    convertedRequest=new ArmeriaServerHttpRequest(ctx,req,factoryWrapper);
  }
 catch (  Exception e) {
    final String path=req.path();
    logger.warn("{} Invalid request path: {}",ctx,path,e);
    future.complete(HttpResponse.of(HttpStatus.BAD_REQUEST,MediaType.PLAIN_TEXT_UTF_8,HttpStatus.BAD_REQUEST + "\nInvalid request path: " + path));
    return Mono.empty();
  }
  final ArmeriaServerHttpResponse convertedResponse=new ArmeriaServerHttpResponse(ctx,future,factoryWrapper,serverHeader);
  return httpHandler.handle(convertedRequest,convertedResponse).doOnSuccessOrError((unused,cause) -> {
    if (cause != null) {
      logger.debug("{} Failed to handle a request",ctx,cause);
      convertedResponse.setComplete(cause).subscribe();
    }
 else {
      convertedResponse.setComplete().subscribe();
    }
  }
);
}
