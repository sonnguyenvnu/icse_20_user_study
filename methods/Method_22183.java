private BeanDefinition createJobPropertiesBeanDefinition(final Element element){
  BeanDefinitionBuilder result=BeanDefinitionBuilder.rootBeanDefinition(JobProperties.class);
  EnumMap<JobProperties.JobPropertiesEnum,String> map=new EnumMap<>(JobProperties.JobPropertiesEnum.class);
  map.put(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER,element.getAttribute(BaseJobBeanDefinitionParserTag.EXECUTOR_SERVICE_HANDLER_ATTRIBUTE));
  map.put(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER,element.getAttribute(BaseJobBeanDefinitionParserTag.JOB_EXCEPTION_HANDLER_ATTRIBUTE));
  result.addConstructorArgValue(map);
  return result.getBeanDefinition();
}
