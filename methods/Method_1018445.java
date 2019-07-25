@SuppressWarnings("rawtypes") public String exp(Class delegateClass){
  String[] beanNames=applicationContext.getBeanNamesForType(delegateClass);
  if (beanNames.length > 1) {
    throw new RuntimeException("More than one Spring bean found for type " + delegateClass);
  }
  return "#{" + beanNames[0] + "}";
}
