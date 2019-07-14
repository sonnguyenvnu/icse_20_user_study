@Bean @ConditionalOnMissingBean(DynamicDataSourceService.class) public InSpringContextDynamicDataSourceService inMemoryDynamicDataSourceService(DynamicDataSourceConfigRepository<InSpringDynamicDataSourceConfig> repository,HswebDataSourceProperties properties,DataSource dataSource){
  DynamicDataSourceProxy dataSourceProxy=new DynamicDataSourceProxy(null,dataSource){
    @Override public DatabaseType getType(){
      if (properties.getDatabaseType() != null) {
        return properties.getDatabaseType();
      }
      return super.getType();
    }
  }
;
  return new InSpringContextDynamicDataSourceService(repository,dataSourceProxy);
}
