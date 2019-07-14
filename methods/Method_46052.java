/** 
 * Recover weight of provider info
 * @param providerInfo ProviderInfo
 * @param weight       recovered weight
 * @return is recover success 
 */
public static boolean recoverWeight(ProviderInfo providerInfo,int weight){
  providerInfo.setStatus(ProviderStatus.RECOVERING);
  providerInfo.setWeight(weight);
  return true;
}
