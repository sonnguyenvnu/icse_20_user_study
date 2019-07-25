private Mono<Endpoints> convert(List<DetectedEndpoint> endpoints){
  if (endpoints.isEmpty()) {
    return Mono.empty();
  }
  Map<String,List<DetectedEndpoint>> endpointsById=endpoints.stream().collect(groupingBy(e -> e.getDefinition().getId()));
  List<Endpoint> result=endpointsById.values().stream().map(endpointList -> {
    endpointList.sort(comparingInt(e -> this.endpoints.indexOf(e.getDefinition())));
    if (endpointList.size() > 1) {
      log.warn("Duplicate endpoints for id '{}' detected. Omitting: {}",endpointList.get(0).getDefinition().getId(),endpointList.subList(1,endpointList.size()));
    }
    return endpointList.get(0).getEndpoint();
  }
).collect(Collectors.toList());
  return Mono.just(Endpoints.of(result));
}
