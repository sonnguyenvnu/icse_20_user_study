static ClusterStateTaskConfig build(Priority priority,TimeValue timeout,SchemaUpdate schemaUpdate){
  return new Basic(priority,timeout){
    @Override public SchemaUpdate schemaUpdate(){
      return schemaUpdate;
    }
  }
;
}
