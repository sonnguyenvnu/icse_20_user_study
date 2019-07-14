private ModifiableConfiguration getGlobalSystemConfig(Backend backend){
  return new ModifiableConfiguration(GraphDatabaseConfiguration.ROOT_NS,backend.getGlobalSystemConfig(),BasicConfiguration.Restriction.GLOBAL);
}
