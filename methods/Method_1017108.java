@Override public Exposed module(final PrimaryComponent primary,final Depends depends,final String id){
  return DaggerSineMetricGeneratorModule_C.builder().primaryComponent(primary).depends(depends).m(new M()).build();
}
