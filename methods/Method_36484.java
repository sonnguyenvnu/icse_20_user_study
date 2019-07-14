protected void configBeanClassLoader(ParserContext parserContext,BeanDefinitionBuilder builder){
  ClassLoader beanClassLoader=parserContext.getReaderContext().getBeanClassLoader();
  builder.addPropertyValue(BEAN_CLASS_LOADER,beanClassLoader);
}
