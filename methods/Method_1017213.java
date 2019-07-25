@Override public Exposed module(PrimaryComponent primary,Depends depends,String id){
  return DaggerBigtableMetricModule_C.builder().primaryComponent(primary).depends(depends).m(new M()).build();
}
