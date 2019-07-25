@Override public Mono<ClientHttpResponse> connect(HttpMethod method,URI uri,Function<? super ClientHttpRequest,Mono<Void>> requestCallback){
  try {
    requireNonNull(method,"method");
    requireNonNull(uri,"uri");
    requireNonNull(requestCallback,"requestCallback");
    final ArmeriaClientHttpRequest request=createRequest(method,uri);
    return requestCallback.apply(request).then(Mono.fromFuture(request.future())).map(ArmeriaHttpClientResponseSubscriber::new).flatMap(s -> Mono.fromFuture(s.httpHeadersFuture()).map(headers -> createResponse(headers,s)));
  }
 catch (  NullPointerException|IllegalArgumentException e) {
    return Mono.error(e);
  }
}
