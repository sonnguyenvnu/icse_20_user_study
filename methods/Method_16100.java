@Bean @ConditionalOnMissingBean(SqlExecutor.class) public SqlExecutor sqlExecutor(){
  return new DefaultJdbcExecutor();
}
