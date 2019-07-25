@Override public ClusterDiscoveryComponent module(PrimaryComponent primary){
  return DaggerStaticListDiscoveryModule_C.builder().primaryComponent(primary).m(new M()).build();
}
