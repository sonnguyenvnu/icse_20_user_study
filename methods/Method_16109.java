@Override public Object postProcessAfterInitialization(Object o,String s) throws BeansException {
  if (o instanceof DataSource) {
    InSpringDynamicDataSourceConfig config=new InSpringDynamicDataSourceConfig();
    config.setId(s);
    config.setBeanName(s);
    config.setName(s);
    add(config);
  }
  return o;
}
