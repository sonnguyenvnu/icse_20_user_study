/** 
 * Read the warmUp weight parameter, decide whether to switch the state to the preheating period, and set the corresponding parameters during the preheating period.
 * @param providerInfo the provider info
 */
public static void processWarmUpWeight(ProviderInfo providerInfo){
  String warmupTimeStr=providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_WARMUP_TIME);
  String warmupWeightStr=providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_WARMUP_WEIGHT);
  String startTimeStr=providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_START_TIME);
  if (StringUtils.isNotBlank(warmupTimeStr) && StringUtils.isNotBlank(warmupWeightStr) && StringUtils.isNotBlank(startTimeStr)) {
    long warmupTime=CommonUtils.parseLong(warmupTimeStr,0);
    int warmupWeight=CommonUtils.parseInt(warmupWeightStr,Integer.parseInt(providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_WEIGHT)));
    long startTime=CommonUtils.parseLong(startTimeStr,0);
    long warmupEndTime=startTime + warmupTime;
    providerInfo.setDynamicAttr(ProviderInfoAttrs.ATTR_WARMUP_WEIGHT,warmupWeight);
    providerInfo.setDynamicAttr(ProviderInfoAttrs.ATTR_WARM_UP_END_TIME,warmupEndTime);
    providerInfo.setStatus(ProviderStatus.WARMING_UP);
  }
  providerInfo.getStaticAttrs().remove(ProviderInfoAttrs.ATTR_WARMUP_TIME);
  providerInfo.getStaticAttrs().remove(ProviderInfoAttrs.ATTR_WARMUP_WEIGHT);
}
