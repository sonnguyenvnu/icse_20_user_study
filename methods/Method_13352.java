@Bean @ConditionalOnMissingBean public DubboGenericServiceFactory dubboGenericServiceFactory(){
  return new DubboGenericServiceFactory();
}
