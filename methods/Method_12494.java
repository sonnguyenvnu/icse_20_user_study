protected Mono<Endpoints> convertResponse(Response response){
  List<Endpoint> endpoints=response.getLinks().entrySet().stream().filter(e -> !e.getKey().equals("self") && !e.getValue().isTemplated()).map(e -> Endpoint.of(e.getKey(),e.getValue().getHref())).collect(Collectors.toList());
  return endpoints.isEmpty() ? Mono.empty() : Mono.just(Endpoints.of(endpoints));
}
