@Override protected String getDatabaseUrl(DatabaseConfiguration dbConfig){
  int port=dbConfig.getDatabasePort();
  return "jdbc:" + dbConfig.getDatabaseType() + "://" + dbConfig.getDatabaseHost() + ((port == 0) ? "" : (":" + port)) + "/" + dbConfig.getDatabaseName() + "?useSSL=" + dbConfig.isUseSSL();
}
