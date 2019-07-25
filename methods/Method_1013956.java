/** 
 * Called when the configuration for the discovery service is changed. If background discovery should be enabled and is currently disabled, the method  {@link AbstractDiscoveryService#startBackgroundDiscovery()} iscalled. If background discovery should be disabled and is currently enabled, the method  {@link AbstractDiscoveryService#stopBackgroundDiscovery()} is called. Inall other cases, nothing happens.
 * @param configProperties configuration properties
 */
protected void modified(@Nullable Map<String,@Nullable Object> configProperties){
  if (configProperties != null) {
    Object property=configProperties.get(DiscoveryService.CONFIG_PROPERTY_BACKGROUND_DISCOVERY);
    if (property != null) {
      boolean enabled=getAutoDiscoveryEnabled(property);
      if (this.backgroundDiscoveryEnabled && !enabled) {
        stopBackgroundDiscovery();
        logger.debug("Background discovery for discovery service '{}' disabled.",this.getClass().getName());
      }
 else       if (!this.backgroundDiscoveryEnabled && enabled) {
        startBackgroundDiscovery();
        logger.debug("Background discovery for discovery service '{}' enabled.",this.getClass().getName());
      }
      this.backgroundDiscoveryEnabled=enabled;
    }
  }
}
