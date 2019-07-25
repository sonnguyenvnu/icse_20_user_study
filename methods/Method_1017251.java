@Override public Exposed module(PrimaryComponent primary,Depends depends,final String id){
  return DaggerMemorySuggestModule_C.builder().primaryComponent(primary).depends(depends).m(new M()).build();
}
