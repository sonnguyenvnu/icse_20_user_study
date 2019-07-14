/** 
 * ??????
 * @param request        ??
 * @param consumerConfig ?????
 * @param providerInfo   ???????
 * @return ????
 */
private int resolveTimeout(SofaRequest request,ConsumerConfig consumerConfig,ProviderInfo providerInfo){
  final String dynamicAlias=consumerConfig.getParameter(DynamicConfigKeys.DYNAMIC_ALIAS);
  if (StringUtils.isNotBlank(dynamicAlias)) {
    String dynamicTimeout=null;
    DynamicConfigManager dynamicConfigManager=DynamicConfigManagerFactory.getDynamicManager(consumerConfig.getAppName(),dynamicAlias);
    if (dynamicConfigManager != null) {
      dynamicTimeout=dynamicConfigManager.getConsumerMethodProperty(request.getInterfaceName(),request.getMethodName(),"timeout");
    }
    if (StringUtils.isNotBlank(dynamicTimeout)) {
      return Integer.parseInt(dynamicTimeout);
    }
  }
  Integer timeout=request.getTimeout();
  if (timeout == null) {
    timeout=consumerConfig.getMethodTimeout(request.getMethodName());
    if (timeout == null || timeout < 0) {
      timeout=(Integer)providerInfo.getDynamicAttr(ATTR_TIMEOUT);
      if (timeout == null) {
        timeout=getIntValue(CONSUMER_INVOKE_TIMEOUT);
      }
    }
  }
  return timeout;
}
