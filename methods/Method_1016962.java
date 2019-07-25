@Override public Exposed module(PrimaryComponent primary,IngestionComponent ingestion,Depends depends,String id){
  return DaggerKafkaConsumerModule_C.builder().primaryComponent(primary).ingestionComponent(ingestion).depends(depends).m(new M(primary,depends)).build();
}
