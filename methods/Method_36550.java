public static String generateSofaServiceBeanName(BeanDefinition definition){
  String interfaceName=(String)definition.getPropertyValues().get(AbstractContractDefinitionParser.INTERFACE_PROPERTY);
  Class clazz=(Class)definition.getPropertyValues().get(AbstractContractDefinitionParser.INTERFACE_CLASS_PROPERTY);
  if (clazz != null) {
    interfaceName=clazz.getCanonicalName();
  }
  String uniqueId=(String)definition.getPropertyValues().get(AbstractContractDefinitionParser.UNIQUE_ID_PROPERTY);
  return generateSofaServiceBeanName(interfaceName,uniqueId);
}
