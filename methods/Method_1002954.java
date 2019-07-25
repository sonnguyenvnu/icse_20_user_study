private Supplier<Mono<Void>> execute(Supplier<HttpRequest> supplier){
  return () -> Mono.defer(() -> {
    assert request == null : request;
    request=supplier.get();
    future.complete(client.execute(request));
    return Mono.fromFuture(request.completionFuture());
  }
);
}
