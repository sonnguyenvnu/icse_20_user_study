public Object invokeMethodInjectionPoint(final MethodInjectionPoint methodRef){
  final BeanReferences[] refNames=methodRef.references;
  final Object[] args=new Object[refNames.length];
  for (int i=0; i < refNames.length; i++) {
    final BeanReferences refName=refNames[i];
    final Object value=pc.lookupMixingScopedBean(beanDefinition,refName);
    args[i]=value;
    if (value == null) {
      if ((beanDefinition.wiringMode == WiringMode.STRICT)) {
        throw new PetiteException("Wiring failed. Beans references: '" + refName + "' not found for method: " + beanDefinition.type.getName() + '#' + methodRef.method.getName());
      }
    }
  }
  try {
    return methodRef.method.invoke(bean,args);
  }
 catch (  Exception ex) {
    throw new PetiteException(ex);
  }
}
