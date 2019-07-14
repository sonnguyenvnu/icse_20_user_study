@Override protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
  DataLoaderRegistry dataLoaderRegistry=registryProvider.get();
  Instrumentation instrumentation=new ChainedInstrumentation(Arrays.asList(GuavaListenableFutureSupport.listenableFutureInstrumentation(),new DataLoaderDispatcherInstrumentation(dataLoaderRegistry),new TracingInstrumentation()));
  GraphQL graphql=GraphQL.newGraphQL(schema).instrumentation(instrumentation).build();
  Map<String,Object> json=readJson(req);
  String query=(String)json.get("query");
  if (query == null) {
    resp.setStatus(400);
    return;
  }
  String operationName=(String)json.get("operationName");
  Map<String,Object> variables=getVariables(json.get("variables"));
  ExecutionInput executionInput=ExecutionInput.newExecutionInput().query(query).operationName(operationName).variables(variables).context(dataLoaderRegistry).build();
  ExecutionResult executionResult=graphql.execute(executionInput);
  resp.setContentType("application/json");
  resp.setStatus(HttpServletResponse.SC_OK);
  GSON.toJson(executionResult.toSpecification(),resp.getWriter());
  logger.info("stats: " + dataLoaderRegistry.getStatistics());
}
