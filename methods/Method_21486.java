@Override public String execute(Client client,Map<String,String> params,QueryAction queryAction) throws Exception {
  SqlElasticRequestBuilder requestBuilder=queryAction.explain();
  ActionRequest request=requestBuilder.request();
  if (requestBuilder instanceof JoinRequestBuilder) {
    ElasticJoinExecutor executor=ElasticJoinExecutor.createJoinExecutor(client,requestBuilder);
    executor.run();
    return ElasticUtils.hitsAsStringResult(executor.getHits(),new MetaSearchResult());
  }
 else   if (requestBuilder instanceof MultiQueryRequestBuilder) {
    ElasticHitsExecutor executor=MultiRequestExecutorFactory.createExecutor(client,(MultiQueryRequestBuilder)requestBuilder);
    executor.run();
    return ElasticUtils.hitsAsStringResult(executor.getHits(),new MetaSearchResult());
  }
 else   if (request instanceof SearchRequest) {
    ActionFuture<SearchResponse> future=client.search((SearchRequest)request);
    SearchResponse response=future.actionGet();
    return response.toString();
  }
 else   if (request instanceof DeleteByQueryRequest) {
    return requestBuilder.get().toString();
  }
 else   if (request instanceof GetIndexRequest) {
    return requestBuilder.getBuilder().execute().actionGet().toString();
  }
 else {
    throw new Exception(String.format("Unsupported ActionRequest provided: %s",request.getClass().getName()));
  }
}
