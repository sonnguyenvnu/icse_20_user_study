@GetMapping("/api") public Mono<Set<ApiDefinition>> apiRules(){
  return Mono.just(GatewayApiDefinitionManager.getApiDefinitions());
}
