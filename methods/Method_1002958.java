private Mono<Void> write(Flux<? extends DataBuffer> publisher){
  return Mono.defer(() -> {
    final HttpResponse response=HttpResponse.of(new HttpResponseProcessor(ctx.eventLoop(),armeriaHeaders.build(),publisher.map(factoryWrapper::toHttpData)));
    future.complete(response);
    return Mono.fromFuture(response.completionFuture()).onErrorResume(cause -> cause instanceof CancelledSubscriptionException || cause instanceof AbortedStreamException,cause -> Mono.empty());
  }
);
}
