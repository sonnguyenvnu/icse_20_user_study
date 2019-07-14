public static SqlScriptFactory createSqlScriptFactory(final JdbcConnectionFactory jdbcConnectionFactory,final Configuration configuration){
  final DatabaseType databaseType=jdbcConnectionFactory.getDatabaseType();
  return new SqlScriptFactory(){
    @Override public SqlScript createSqlScript(    LoadableResource resource,    boolean mixed){
      return new ParserSqlScript(createParser(jdbcConnectionFactory,configuration),resource,mixed);
    }
  }
;
}
