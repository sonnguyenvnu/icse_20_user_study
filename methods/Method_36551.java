public static String generateSofaServiceBeanName(Class<?> interfaceType,String uniqueId){
  return generateSofaServiceBeanName(interfaceType.getCanonicalName(),uniqueId);
}
