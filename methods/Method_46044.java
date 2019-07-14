/** 
 * Remove dimension stat by consumerConfig and providerInfo
 * @param consumerConfig ConsumerConfig
 * @param providerInfo   ProviderInfo
 */
public static void removeInvocationStat(ConsumerConfig consumerConfig,ProviderInfo providerInfo){
  removeInvocationStat(new InvocationStatDimension(providerInfo,consumerConfig));
}
