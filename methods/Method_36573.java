@Override protected String resolveId(Element element,AbstractBeanDefinition definition,ParserContext parserContext) throws BeanDefinitionStoreException {
  return SofaBeanNameGenerator.generateSofaServiceBeanName(definition);
}
