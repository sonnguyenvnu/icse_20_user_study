private BeanDefinition createLiteJobConfiguration(final ParserContext parserContext,final Element element){
  return createLiteJobConfigurationBeanDefinition(parserContext,element,createJobCoreBeanDefinition(element));
}
