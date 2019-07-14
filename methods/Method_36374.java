private Map<String,BeanDefinition> getBeanDefinitionsForType(ConfigurableListableBeanFactory beanFactory,Class... types){
  Map<String,BeanDefinition> map=new HashMap<>();
  for (  Class type : types) {
    String[] beanNamesForType=beanFactory.getBeanNamesForType(type);
    List<String> beanDefinitionNames=Arrays.asList(beanFactory.getBeanDefinitionNames());
    for (    String beanName : beanNamesForType) {
      if (notInWhiteNameList(beanName) && beanDefinitionNames.contains(beanName)) {
        map.put(beanName,beanFactory.getBeanDefinition(beanName));
      }
    }
  }
  return map;
}
