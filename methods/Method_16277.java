@Bean @ConditionalOnClass(name="oracle.jdbc.driver.OracleDriver") public OracleTableMetaDataParser oracleTableMetaParser(){
  return new OracleTableMetaDataParser(sqlExecutor);
}
