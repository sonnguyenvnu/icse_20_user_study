/** 
 * Creates mixed scope message.
 */
protected String createMixingMessage(final BeanDefinition targetBeanDefinition,final BeanDefinition refBeanDefinition){
  return "Scopes mixing detected: " + refBeanDefinition.name + "@" + refBeanDefinition.scope.getClass().getSimpleName() + " -> " + targetBeanDefinition.name + "@" + targetBeanDefinition.scope.getClass().getSimpleName();
}
