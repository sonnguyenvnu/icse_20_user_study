private List<Callback> prepareCallbacks(Database database,ResourceProvider resourceProvider,JdbcConnectionFactory jdbcConnectionFactory,SqlScriptFactory sqlScriptFactory){
  List<Callback> effectiveCallbacks=new ArrayList<>();
  effectiveCallbacks.addAll(Arrays.asList(configuration.getCallbacks()));
  if (!configuration.isSkipDefaultCallbacks()) {
    SqlScriptExecutorFactory sqlScriptExecutorFactory=DatabaseFactory.createSqlScriptExecutorFactory(jdbcConnectionFactory);
    effectiveCallbacks.addAll(new SqlScriptCallbackFactory(resourceProvider,sqlScriptExecutorFactory,sqlScriptFactory,configuration).getCallbacks());
  }
  return effectiveCallbacks;
}
