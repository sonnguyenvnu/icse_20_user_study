@Bean @ConditionalOnClass(name="com.mysql.jdbc.Driver") public MysqlTableMetaDataParser mysqlTableMetaDataParser(){
  return new MysqlTableMetaDataParser(sqlExecutor);
}
