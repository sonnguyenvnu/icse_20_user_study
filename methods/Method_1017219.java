@Override public Exposed module(PrimaryComponent primary,Depends depends,String id){
  final SchemaComponent schema=this.schema.module(primary);
  return DaggerDatastaxMetricModule_C.builder().primaryComponent(primary).schemaComponent(schema).depends(depends).m(new M()).build();
}
