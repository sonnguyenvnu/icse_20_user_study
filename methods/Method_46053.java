/** 
 * Degrade weight of provider info
 * @param providerInfo ProviderInfo
 * @param weight       degraded weight
 * @return is degrade success
 */
public static boolean degradeWeight(ProviderInfo providerInfo,int weight){
  providerInfo.setStatus(ProviderStatus.DEGRADED);
  providerInfo.setWeight(weight);
  return true;
}
