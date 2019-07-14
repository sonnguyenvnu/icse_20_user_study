/** 
 * ???????
 * @param consumerConfig ????
 * @param providerInfo   ????
 * @return ???????????????????
 */
public static InvocationStat getInvocationStat(ConsumerConfig consumerConfig,ProviderInfo providerInfo){
  String appName=consumerConfig.getAppName();
  if (appName == null) {
    return null;
  }
  if (FaultToleranceConfigManager.isRegulationEffective(appName)) {
    return getInvocationStat(new InvocationStatDimension(providerInfo,consumerConfig));
  }
  return null;
}
