/** 
 * Called on component activation, if the implementation of this class is an OSGi declarative service and does not override the method. The method implementation calls  {@link AbstractDiscoveryService#startBackgroundDiscovery()} if backgrounddiscovery is enabled by default and not overridden by the configuration.
 * @param configProperties configuration properties
 */
protected void activate(@Nullable Map<String,@Nullable Object> configProperties){
  if (configProperties != null) {
    Object property=configProperties.get(DiscoveryService.CONFIG_PROPERTY_BACKGROUND_DISCOVERY);
    if (property != null) {
      this.backgroundDiscoveryEnabled=getAutoDiscoveryEnabled(property);
    }
  }
  if (this.backgroundDiscoveryEnabled) {
    startBackgroundDiscovery();
    logger.debug("Background discovery for discovery service '{}' enabled.",this.getClass().getName());
  }
}
