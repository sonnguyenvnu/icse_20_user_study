@Override public Mono<Long> count(){
  return elasticsearchOperations.count(Query.findAll(),entityInformation.getJavaType(),entityInformation.getIndexName(),entityInformation.getType());
}
