@Override public Exposed module(final PrimaryComponent primary,final Depends depends,final String id){
  final BackendType backendType=backendTypeBuilder.get();
  return DaggerElasticsearchMetadataModule_C.builder().primaryComponent(primary).depends(depends).connectionModule(connection).m(new M(groups,templateName,backendType,writesPerSecond,rateLimitSlowStartSeconds,writeCacheDurationMinutes)).build();
}
