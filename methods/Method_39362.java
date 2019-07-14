/** 
 * Registers property injection point.
 * @param beanName bean name
 * @param property property name
 * @param reference explicit injection reference, may be <code>null</code>
 */
public void registerPetitePropertyInjectionPoint(final String beanName,final String property,final String reference){
  BeanDefinition beanDefinition=lookupExistingBeanDefinition(beanName);
  ClassDescriptor cd=ClassIntrospector.get().lookup(beanDefinition.type);
  PropertyDescriptor propertyDescriptor=cd.getPropertyDescriptor(property,true);
  if (propertyDescriptor == null) {
    throw new PetiteException("Property not found: " + beanDefinition.type.getName() + '#' + property);
  }
  BeanReferences ref=referencesResolver.resolveReferenceFromValue(propertyDescriptor,reference);
  PropertyInjectionPoint pip=new PropertyInjectionPoint(propertyDescriptor,ref);
  beanDefinition.addPropertyInjectionPoint(pip);
}
