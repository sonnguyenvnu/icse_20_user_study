private List<BeanDefinition> createJobListeners(final Element element){
  Element listenerElement=DomUtils.getChildElementByTagName(element,BaseJobBeanDefinitionParserTag.LISTENER_TAG);
  Element distributedListenerElement=DomUtils.getChildElementByTagName(element,BaseJobBeanDefinitionParserTag.DISTRIBUTED_LISTENER_TAG);
  List<BeanDefinition> result=new ManagedList<>(2);
  if (null != listenerElement) {
    BeanDefinitionBuilder factory=BeanDefinitionBuilder.rootBeanDefinition(listenerElement.getAttribute(BaseJobBeanDefinitionParserTag.CLASS_ATTRIBUTE));
    factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    result.add(factory.getBeanDefinition());
  }
  if (null != distributedListenerElement) {
    BeanDefinitionBuilder factory=BeanDefinitionBuilder.rootBeanDefinition(distributedListenerElement.getAttribute(BaseJobBeanDefinitionParserTag.CLASS_ATTRIBUTE));
    factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    factory.addConstructorArgValue(distributedListenerElement.getAttribute(BaseJobBeanDefinitionParserTag.DISTRIBUTED_LISTENER_STARTED_TIMEOUT_MILLISECONDS_ATTRIBUTE));
    factory.addConstructorArgValue(distributedListenerElement.getAttribute(BaseJobBeanDefinitionParserTag.DISTRIBUTED_LISTENER_COMPLETED_TIMEOUT_MILLISECONDS_ATTRIBUTE));
    result.add(factory.getBeanDefinition());
  }
  return result;
}
