public static SqlScriptExecutorFactory createSqlScriptExecutorFactory(final JdbcConnectionFactory jdbcConnectionFactory){
  final DatabaseType databaseType=jdbcConnectionFactory.getDatabaseType();
  if (DatabaseType.ORACLE == databaseType) {
    return new SqlScriptExecutorFactory(){
      @Override public SqlScriptExecutor createSqlScriptExecutor(      Connection connection){
        return new OracleSqlScriptExecutor(new JdbcTemplate(connection,databaseType));
      }
    }
;
  }
  return new SqlScriptExecutorFactory(){
    @Override public SqlScriptExecutor createSqlScriptExecutor(    Connection connection){
      return new DefaultSqlScriptExecutor(new JdbcTemplate(connection,databaseType));
    }
  }
;
}
