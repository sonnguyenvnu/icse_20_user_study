@Override public Object postProcessBeforeInitialization(final Object bean,String beanName) throws BeansException {
  if (bean instanceof ClientFactoryAware) {
    ((ClientFactoryAware)bean).setClientFactory(clientFactory);
  }
  ReflectionUtils.doWithFields(bean.getClass(),new ReflectionUtils.FieldCallback(){
    @Override public void doWith(    Field field) throws IllegalArgumentException, IllegalAccessException {
      if (field.getType().equals(ClientFactory.class)) {
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field,bean,clientFactory);
      }
 else       if ((clientFactory instanceof ClientFactoryImpl) && ((ClientFactoryImpl)clientFactory).getAllClientTypes().contains(field.getType())) {
        Object client=clientFactory.getClient(field.getType());
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field,bean,client);
      }
 else {
        throw new RuntimeException("Field annotated by ClientFactorySetter must be of type" + " ClientFactory or client store in the ClientFactory.");
      }
    }
  }
,new ReflectionUtils.FieldFilter(){
    @Override public boolean matches(    Field field){
      return !Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(SofaClientFactory.class);
    }
  }
);
  return bean;
}
