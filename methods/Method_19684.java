@Bean(name="oracledatasource") @ConfigurationProperties("spring.datasource.druid.oracle") public DataSource oracleDataSource(){
  return DruidDataSourceBuilder.create().build();
}
