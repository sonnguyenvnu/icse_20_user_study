/** 
 * ???ProviderConfig??
 * @param providerConfig the provider config
 */
public static void invalidateProviderConfig(ProviderBootstrap providerConfig){
  EXPORTED_PROVIDER_CONFIGS.remove(providerConfig);
}
