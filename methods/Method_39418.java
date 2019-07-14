@Override public void register(final BeanDefinition beanDefinition,final Object bean){
  instances.put(beanDefinition.name(),new BeanData(pc,beanDefinition,bean));
}
