protected void wireSets(){
  for (  final SetInjectionPoint sip : definition().sets) {
    String[] beanNames=pc.resolveBeanNamesForType(sip.targetClass);
    Collection beans=sip.createSet(beanNames.length);
    for (    String beanName : beanNames) {
      if (!beanName.equals(definition().name)) {
        Object value=pc.getBean(beanName);
        beans.add(value);
      }
    }
    final Setter setter=sip.propertyDescriptor.getSetter(true);
    try {
      setter.invokeSetter(bean,beans);
    }
 catch (    Exception ex) {
      throw new PetiteException("Wiring failed",ex);
    }
  }
}
