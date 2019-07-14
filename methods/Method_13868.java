private static String getDatabaseUrl(DatabaseConfiguration dbConfig){
  int port=dbConfig.getDatabasePort();
  return "jdbc:" + dbConfig.getDatabaseType().toLowerCase() + "://" + dbConfig.getDatabaseHost() + ((port == 0) ? "" : (":" + port)) + "/" + dbConfig.getDatabaseName();
}
