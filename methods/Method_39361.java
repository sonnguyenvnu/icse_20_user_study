/** 
 * Registers constructor injection point.
 * @param beanName bean name
 * @param paramTypes constructor parameter types, may be <code>null</code>
 * @param references references for arguments
 */
public void registerPetiteCtorInjectionPoint(final String beanName,final Class[] paramTypes,final String[] references){
  BeanDefinition beanDefinition=lookupExistingBeanDefinition(beanName);
  ClassDescriptor cd=ClassIntrospector.get().lookup(beanDefinition.type);
  Constructor constructor=null;
  if (paramTypes == null) {
    CtorDescriptor[] ctors=cd.getAllCtorDescriptors();
    if (ctors != null && ctors.length > 0) {
      if (ctors.length > 1) {
        throw new PetiteException(ctors.length + " suitable constructor found as injection point for: " + beanDefinition.type.getName());
      }
      constructor=ctors[0].getConstructor();
    }
  }
 else {
    CtorDescriptor ctorDescriptor=cd.getCtorDescriptor(paramTypes,true);
    if (ctorDescriptor != null) {
      constructor=ctorDescriptor.getConstructor();
    }
  }
  if (constructor == null) {
    throw new PetiteException("Constructor not found: " + beanDefinition.type.getName());
  }
  BeanReferences[] ref=referencesResolver.resolveReferenceFromValues(constructor,references);
  beanDefinition.ctor=new CtorInjectionPoint(constructor,ref);
}
