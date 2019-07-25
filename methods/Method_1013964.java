@Deactivate protected void deactivate(){
  active.set(false);
  for (  final DiscoveryService discoveryService : discoveryServicesAll) {
    removeDiscoveryServiceActivated(discoveryService);
  }
  this.listeners.clear();
  this.cachedResults.clear();
}
