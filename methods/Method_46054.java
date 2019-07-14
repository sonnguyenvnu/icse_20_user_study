/** 
 * Recover weight of provider info, and set default status
 * @param providerInfo ProviderInfo
 * @param originWeight origin weight
 */
public static void recoverOriginWeight(ProviderInfo providerInfo,int originWeight){
  providerInfo.setStatus(ProviderStatus.AVAILABLE);
  providerInfo.setWeight(originWeight);
}
