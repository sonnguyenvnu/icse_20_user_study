@Override public Exposed module(PrimaryComponent primary,Depends depends,final String id){
  final BackendType backendType=type.get();
  return DaggerElasticsearchSuggestModule_C.builder().primaryComponent(primary).depends(depends).connectionModule(connection).m(new M(backendType)).build();
}
