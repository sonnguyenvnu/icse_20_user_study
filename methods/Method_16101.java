@Bean @ConfigurationProperties(prefix="spring.datasource") public HswebDataSourceProperties hswebDataSouceProperties(){
  return new HswebDataSourceProperties();
}
