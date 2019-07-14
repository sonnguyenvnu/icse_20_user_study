public static ElasticJoinExecutor createJoinExecutor(Client client,SqlElasticRequestBuilder requestBuilder){
  if (requestBuilder instanceof HashJoinElasticRequestBuilder) {
    HashJoinElasticRequestBuilder hashJoin=(HashJoinElasticRequestBuilder)requestBuilder;
    return new HashJoinElasticExecutor(client,hashJoin);
  }
 else   if (requestBuilder instanceof NestedLoopsElasticRequestBuilder) {
    NestedLoopsElasticRequestBuilder nestedLoops=(NestedLoopsElasticRequestBuilder)requestBuilder;
    return new NestedLoopsElasticExecutor(client,nestedLoops);
  }
 else {
    throw new RuntimeException("Unsuported requestBuilder of type: " + requestBuilder.getClass());
  }
}
