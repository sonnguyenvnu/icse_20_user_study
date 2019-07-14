public void initializeFromConfig(EngineConfig config){
  _config=config;
  _facets=config.getFacetConfigs().stream().map(c -> c.apply(_project)).collect(Collectors.toList());
}
