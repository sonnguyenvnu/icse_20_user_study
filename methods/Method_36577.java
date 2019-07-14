@Override public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
  Arrays.stream(beanFactory.getBeanDefinitionNames()).collect(Collectors.toMap(Function.identity(),beanFactory::getBeanDefinition)).forEach((key,value) -> transformSofaBeanDefinition(key,value,beanFactory));
}
