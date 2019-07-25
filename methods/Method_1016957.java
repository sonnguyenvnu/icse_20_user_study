@Override public Exposed module(PrimaryComponent primary,IngestionComponent ingestion,Depends depends,String id){
  return DaggerCollectdConsumerModule_C.builder().primaryComponent(primary).ingestionComponent(ingestion).depends(depends).m(new M()).build();
}
