@GetMapping("/api") public Set<ApiDefinition> apiRules(){
  return GatewayApiDefinitionManager.getApiDefinitions();
}
