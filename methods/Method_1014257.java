/** 
 * Deactivates the  {@link SceneDiscoveryService} and removes the {@link DiscoveryResult}s.
 */
@Override public void deactivate(){
  logger.debug("deactivate discovery service for scene type {} remove thing tyspes {}",sceneType,super.getSupportedThingTypes());
  removeOlderResults(new Date().getTime());
}
