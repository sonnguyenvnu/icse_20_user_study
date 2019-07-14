/** 
 * Collect the RPC server information.
 * @param rpcServerMetricsModel server information model
 */
public void collectServer(RpcServerLookoutModel rpcServerMetricsModel){
  try {
    Id methodProviderId=createMethodProviderId(rpcServerMetricsModel);
    MixinMetric methodProviderMetric=Lookout.registry().mixinMetric(methodProviderId);
    recordCounterAndTimer(methodProviderMetric,rpcServerMetricsModel);
  }
 catch (  Throwable t) {
    LOGGER.error(LogCodes.getLog(LogCodes.ERROR_METRIC_REPORT_ERROR),t);
  }
}
