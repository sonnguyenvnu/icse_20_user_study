@Bean(initMethod="init",destroyMethod="destroy",value="datasource") @Primary public AtomikosDataSourceBean datasource(){
  return new AtomikosDataSourceBean();
}
