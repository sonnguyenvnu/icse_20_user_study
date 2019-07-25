/** 
 * Creates all bridges from site_config and connects them (joining the bridge clusters)
 * @param bridge_configs A list of bridge configurations
 * @param bridge_name The name of the local bridge channel, prefixed with '_'.
 * @param my_site_id The ID of this site
 * @throws Throwable
 */
public void start(List<RelayConfig.BridgeConfig> bridge_configs,String bridge_name,final String my_site_id) throws Throwable {
  if (done) {
    log.trace(relay.getLocalAddress() + ": will not start the Relayer as stop() has been called");
    return;
  }
  try {
    for (    RelayConfig.BridgeConfig bridge_config : bridge_configs) {
      Bridge bridge=new Bridge(bridge_config.createChannel(),bridge_config.getClusterName(),bridge_name,() -> new SiteUUID(UUID.randomUUID(),null,my_site_id));
      bridges.add(bridge);
    }
    for (    Bridge bridge : bridges)     bridge.start();
  }
 catch (  Throwable t) {
    stop();
    throw t;
  }
 finally {
    if (done) {
      log.trace(relay.getLocalAddress() + ": stop() was called while starting the relayer; stopping the relayer now");
      stop();
    }
  }
}
