@Override public SQLConfig newSQLConfig() throws Exception {
  return DemoSQLConfig.newSQLConfig(method,table,sqlRequest,joinList);
}
