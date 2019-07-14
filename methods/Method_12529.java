private static ExchangeFilterFunction setInstance(Mono<Instance> instance){
  return (request,next) -> instance.map(i -> ClientRequest.from(request).attribute(ATTRIBUTE_INSTANCE,i).build()).switchIfEmpty(Mono.error(() -> new ResolveInstanceException("Could not resolve Instance"))).flatMap(next::exchange);
}
