@Primary @Bean(name="mysqldatasource") @ConfigurationProperties("spring.datasource.druid.mysql") public DataSource mysqlDataSource(){
  return DruidDataSourceBuilder.create().build();
}
