protected void wireProperties(){
  for (  PropertyInjectionPoint pip : beanDefinition.properties) {
    final BeanReferences refNames=pip.references;
    final Object value=pc.lookupMixingScopedBean(this.definition(),refNames);
    if (value == null) {
      if ((beanDefinition.wiringMode == WiringMode.STRICT)) {
        throw new PetiteException("Wiring failed. Beans references: '" + refNames + "' not found for property: " + beanDefinition.type.getName() + '#' + pip.propertyDescriptor.getName());
      }
      continue;
    }
    final Setter setter=pip.propertyDescriptor.getSetter(true);
    try {
      setter.invokeSetter(bean,value);
    }
 catch (    Exception ex) {
      throw new PetiteException("Wiring failed",ex);
    }
  }
}
