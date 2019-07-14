private void registerTagParser(SofaBootTagNameSupport tagNameSupport){
  if (tagNameSupport instanceof BeanDefinitionParser) {
    registerBeanDefinitionParser(tagNameSupport.supportTagName(),(BeanDefinitionParser)tagNameSupport);
  }
 else   if (tagNameSupport instanceof BeanDefinitionDecorator) {
    registerBeanDefinitionDecoratorForAttribute(tagNameSupport.supportTagName(),(BeanDefinitionDecorator)tagNameSupport);
  }
 else {
    logger.error(tagNameSupport.getClass() + " tag name supported [" + tagNameSupport.supportTagName() + "] parser are not instance of " + BeanDefinitionParser.class + "or " + BeanDefinitionDecorator.class);
  }
}
