@Override public Exposed module(final PrimaryComponent primary,final Depends depends,final String id){
  return DaggerRandomEventMetricGeneratorModule_C.builder().primaryComponent(primary).depends(depends).m(new M()).build();
}
