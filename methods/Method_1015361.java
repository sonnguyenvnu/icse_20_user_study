public WebClient instance(Mono<Instance> instance){
  return webClient.mutate().filters(filters -> filters.add(0,InstanceExchangeFilterFunctions.setInstance(instance))).build();
}
