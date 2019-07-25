private Mono<Endpoints> convert(Response response){
  List<Endpoint> endpoints=response.getLinks().entrySet().stream().filter(e -> !e.getKey().equals("self") && !e.getValue().isTemplated()).map(e -> Endpoint.of(e.getKey(),e.getValue().getHref())).collect(Collectors.toList());
  if (endpoints.isEmpty()) {
    return Mono.empty();
  }
 else {
    return Mono.just(Endpoints.of(endpoints));
  }
}
