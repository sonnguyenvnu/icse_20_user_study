/** 
 * Creates  {@link jodd.petite.BeanDefinition} on{@link #registerPetiteBean(Class,String,Class,WiringMode,boolean,Consumer) bean registration}. This is a hook for modifying the bean data, like passing proxifed class etc. By default returns new instance of  {@link jodd.petite.BeanDefinition}.
 */
protected <T>BeanDefinition createBeanDefinitionForRegistration(final String name,final Class<T> type,final Scope scope,final WiringMode wiringMode,final Consumer<T> consumer){
  return new BeanDefinition<>(name,type,scope,wiringMode,consumer);
}
