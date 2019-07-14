/** 
 * Registers method injection point.
 * @param beanName bean name
 * @param methodName method name
 * @param arguments method arguments, may be <code>null</code>
 * @param references injection references
 */
public void registerPetiteMethodInjectionPoint(final String beanName,final String methodName,final Class[] arguments,final String[] references){
  BeanDefinition beanDefinition=lookupExistingBeanDefinition(beanName);
  ClassDescriptor cd=ClassIntrospector.get().lookup(beanDefinition.type);
  Method method=null;
  if (arguments == null) {
    MethodDescriptor[] methods=cd.getAllMethodDescriptors(methodName);
    if (methods != null && methods.length > 0) {
      if (methods.length > 1) {
        throw new PetiteException(methods.length + " suitable methods found as injection points for: " + beanDefinition.type.getName() + '#' + methodName);
      }
      method=methods[0].getMethod();
    }
  }
 else {
    MethodDescriptor md=cd.getMethodDescriptor(methodName,arguments,true);
    if (md != null) {
      method=md.getMethod();
    }
  }
  if (method == null) {
    throw new PetiteException("Method not found: " + beanDefinition.type.getName() + '#' + methodName);
  }
  BeanReferences[] ref=referencesResolver.resolveReferenceFromValues(method,references);
  MethodInjectionPoint mip=new MethodInjectionPoint(method,ref);
  beanDefinition.addMethodInjectionPoint(mip);
}
