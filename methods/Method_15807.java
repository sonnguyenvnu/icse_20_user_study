@Override public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
  if (bean instanceof ResponseJudgeForServerIdDefinition) {
    ResponseJudgeForServerIdDefinition definition=((ResponseJudgeForServerIdDefinition)bean);
    setJudgeForServerId(definition.getServerId(),definition);
  }
  if (bean instanceof ResponseConvertForServerIdDefinition) {
    ResponseConvertForServerIdDefinition definition=((ResponseConvertForServerIdDefinition)bean);
    setConvertForServerId(definition.getServerId(),definition);
  }
  if (bean instanceof ResponseJudgeForProviderDefinition) {
    ResponseJudgeForProviderDefinition definition=((ResponseJudgeForProviderDefinition)bean);
    setJudgeForProvider(definition.getProvider(),definition);
  }
  if (bean instanceof ResponseConvertForProviderDefinition) {
    ResponseConvertForProviderDefinition definition=((ResponseConvertForProviderDefinition)bean);
    setConvertForProvider(definition.getProvider(),definition);
  }
  return bean;
}
