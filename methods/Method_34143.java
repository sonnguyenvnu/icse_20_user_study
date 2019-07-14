@Bean public FactoryBean<ConsumerTokenServices> consumerTokenServices() throws Exception {
  return new AbstractFactoryBean<ConsumerTokenServices>(){
    @Override public Class<?> getObjectType(){
      return ConsumerTokenServices.class;
    }
    @Override protected ConsumerTokenServices createInstance() throws Exception {
      return getEndpointsConfigurer().getConsumerTokenServices();
    }
  }
;
}
