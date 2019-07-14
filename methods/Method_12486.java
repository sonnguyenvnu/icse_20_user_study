@Override public Mono<Endpoints> detectEndpoints(Instance instance){
  Mono<Endpoints> result=Mono.empty();
  for (  EndpointDetectionStrategy delegate : delegates) {
    result=result.switchIfEmpty(delegate.detectEndpoints(instance));
  }
  return result.switchIfEmpty(Mono.just(Endpoints.empty()));
}
