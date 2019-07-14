@Bean @Primary public DynamicDataSourceConfigRepository inDBDataSourceRepository(DataSourceConfigService dataSourceConfigService){
  return new InDBDataSourceRepository(dataSourceConfigService);
}
