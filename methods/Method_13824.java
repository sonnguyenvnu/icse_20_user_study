private static String getQuerySource(DatabaseQueryInfo dbQueryInfo){
  String dbType=dbQueryInfo.getDbConfig().getDatabaseType();
  return DatabaseService.get(dbType).getDatabaseUrl(dbQueryInfo.getDbConfig());
}
