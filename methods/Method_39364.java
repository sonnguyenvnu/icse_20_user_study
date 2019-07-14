/** 
 * Registers destroy method.
 * @param beanName bean name
 * @param destroyMethodNames destroy method names
 */
public void registerPetiteDestroyMethods(final String beanName,String... destroyMethodNames){
  BeanDefinition beanDefinition=lookupExistingBeanDefinition(beanName);
  ClassDescriptor cd=ClassIntrospector.get().lookup(beanDefinition.type);
  if (destroyMethodNames == null) {
    destroyMethodNames=StringPool.EMPTY_ARRAY;
  }
  int total=destroyMethodNames.length;
  DestroyMethodPoint[] destroyMethodPoints=new DestroyMethodPoint[total];
  int i;
  for (i=0; i < destroyMethodNames.length; i++) {
    MethodDescriptor md=cd.getMethodDescriptor(destroyMethodNames[i],ClassUtil.EMPTY_CLASS_ARRAY,true);
    if (md == null) {
      throw new PetiteException("Destroy method not found: " + beanDefinition.type.getName() + '#' + destroyMethodNames[i]);
    }
    destroyMethodPoints[i]=new DestroyMethodPoint(md.getMethod());
  }
  beanDefinition.addDestroyMethodPoints(destroyMethodPoints);
}
