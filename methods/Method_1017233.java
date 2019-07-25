@Override public SchemaComponent module(PrimaryComponent primary){
  return DaggerNextGenSchemaModule_C.builder().primaryComponent(primary).m(new M()).build();
}
