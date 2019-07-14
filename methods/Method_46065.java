/** 
 * Collect the RPC client information.
 * @param providerConfig client information model
 */
public void collectProvderPubInfo(final ProviderConfig providerConfig){
  try {
    Id providerConfigId=rpcLookoutId.fetchProviderPubId();
    Lookout.registry().info(providerConfigId,new Info<ProviderConfig>(){
      @Override public ProviderConfig value(){
        return providerConfig;
      }
    }
);
  }
 catch (  Throwable t) {
    LOGGER.error(LogCodes.getLog(LogCodes.ERROR_METRIC_REPORT_ERROR),t);
  }
}
