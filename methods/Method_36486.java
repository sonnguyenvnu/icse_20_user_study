/** 
 * Resolve the ID for the supplied  {@link BeanDefinition}. <p>When using  {@link #shouldGenerateId generation}, a name is generated automatically. Otherwise, the ID is extracted from the "id" attribute, potentially with a {@link #shouldGenerateIdAsFallback() fallback} to a generated id.
 * @param element       the element that the bean definition has been built from
 * @param definition    the bean definition to be registered
 * @param parserContext the object encapsulating the current state of the parsing process;provides access to a  {@link BeanDefinitionRegistry}
 * @return the resolved id
 * @throws BeanDefinitionStoreException if no unique name could be generatedfor the given bean definition
 */
protected String resolveId(Element element,AbstractBeanDefinition definition,ParserContext parserContext) throws BeanDefinitionStoreException {
  if (shouldGenerateId()) {
    return parserContext.getReaderContext().generateBeanName(definition);
  }
 else {
    String id=element.getAttribute(ID_ATTRIBUTE);
    if (!StringUtils.hasText(id) && shouldGenerateIdAsFallback()) {
      id=parserContext.getReaderContext().generateBeanName(definition);
    }
    return id;
  }
}
