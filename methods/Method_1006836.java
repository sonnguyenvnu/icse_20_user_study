public Class<? extends BaseRepository> build(Class<? extends BaseRepository> clzz){
  String beanName=SchemaTransformRepository.class.getName();
  registry.removeBeanDefinition(beanName);
  for (  BaseRepository baseRepository : HealthChecker.getRepositoryList()) {
    if (baseRepository.getClz() == SchemaTransform.class) {
      HealthChecker.getRepositoryList().remove(baseRepository);
      break;
    }
  }
  beanName=clzz.getName();
  Type[] types=clzz.getGenericInterfaces();
  ParameterizedType parameterized=(ParameterizedType)types[0];
  Class clazz=(Class)parameterized.getActualTypeArguments()[0];
{
    Class[] clzArr=clazz.getInterfaces();
    boolean flag=false;
    for (    Class clz : clzArr) {
      if (clz == Transformed.class) {
        flag=true;
        break;
      }
    }
    if (!flag)     throw new RuntimeException("SchemaTransform Customized Class has to implements x7.core.bean.Transformed");
  }
  if (registry.containsBeanDefinition(beanName))   return clzz;
  BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition(clzz);
  GenericBeanDefinition definition=(GenericBeanDefinition)builder.getRawBeanDefinition();
  definition.getPropertyValues().add("objectType",clzz);
  definition.getPropertyValues().add("clz",clazz);
  definition.setBeanClass(RepositoryProxy.class);
  definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
  registry.registerBeanDefinition(beanName,definition);
  return clzz;
}
