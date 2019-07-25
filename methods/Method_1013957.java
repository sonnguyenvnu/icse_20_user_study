/** 
 * Called on component deactivation, if the implementation of this class is an OSGi declarative service and does not override the method. The method implementation calls  {@link AbstractDiscoveryService#stopBackgroundDiscovery()} if backgrounddiscovery is enabled at the time of component deactivation.
 */
protected void deactivate(){
  if (this.backgroundDiscoveryEnabled) {
    stopBackgroundDiscovery();
  }
}
