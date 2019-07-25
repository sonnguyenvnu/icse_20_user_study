@Override public SchemaComponent module(PrimaryComponent primary){
  return DaggerLegacySchemaModule_C.builder().primaryComponent(primary).m(new M()).build();
}
