@Override public ClusterDiscoveryComponent module(PrimaryComponent primary){
  return DaggerSrvRecordDiscoveryModule_C.builder().primaryComponent(primary).m(new M()).build();
}
