@Bean public BeanPostProcessor switcherInitProcessor(){
  return new BeanPostProcessor(){
    @Override public Object postProcessBeforeInitialization(    Object bean,    String beanName) throws BeansException {
      return bean;
    }
    @Override public Object postProcessAfterInitialization(    Object bean,    String beanName) throws BeansException {
      if (bean instanceof DynamicDataSourceService) {
        DataSourceHolder.dynamicDataSourceService=((DynamicDataSourceService)bean);
      }
      if (bean instanceof DataSourceSwitcher) {
        DataSourceHolder.dataSourceSwitcher=((DataSourceSwitcher)bean);
      }
      if (bean instanceof TableSwitcher) {
        DataSourceHolder.tableSwitcher=((TableSwitcher)bean);
      }
      if (bean instanceof DatabaseSwitcher) {
        DataSourceHolder.databaseSwitcher=((DatabaseSwitcher)bean);
      }
      return bean;
    }
  }
;
}
