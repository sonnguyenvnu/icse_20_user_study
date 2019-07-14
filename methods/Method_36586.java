/** 
 * {@link AnnotatedGenericBeanDefinition}{@link ScannedGenericBeanDefinition}{@link GenericBeanDefinition}{@link org.springframework.beans.factory.support.ChildBeanDefinition}{@link org.springframework.beans.factory.support.RootBeanDefinition}
 * @param beanDefinition resolve bean class type from bean definition
 * @return
 */
private Class<?> resolveBeanClassType(BeanDefinition beanDefinition){
  Class<?> clazz=null;
  if (beanDefinition instanceof AnnotatedBeanDefinition) {
    AnnotationMetadata annotationMetadata=((AnnotatedBeanDefinition)beanDefinition).getMetadata();
    try {
      String className=annotationMetadata.getClassName();
      clazz=StringUtils.isEmpty(className) ? null : ClassUtils.forName(className,null);
    }
 catch (    Throwable throwable) {
    }
  }
  if (clazz == null) {
    try {
      clazz=((AbstractBeanDefinition)beanDefinition).getBeanClass();
    }
 catch (    IllegalStateException ex) {
      try {
        String className=beanDefinition.getBeanClassName();
        clazz=StringUtils.isEmpty(className) ? null : ClassUtils.forName(className,null);
      }
 catch (      Throwable throwable) {
      }
    }
  }
  if (ClassUtils.isCglibProxyClass(clazz)) {
    return clazz.getSuperclass();
  }
 else {
    return clazz;
  }
}
