@Override public SQLConfig newSQLConfig(boolean isProcedure) throws Exception {
  return DemoSQLConfig.newSQLConfig(method,table,sqlRequest,joinList,isProcedure);
}
