public ModifiableConfiguration buildGlobalWrite(WriteConfiguration config){
  return build(GraphDatabaseConfiguration.ROOT_NS,config,BasicConfiguration.Restriction.GLOBAL);
}
