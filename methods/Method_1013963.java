@Activate protected void activate(){
  active.set(true);
  for (  final DiscoveryService discoveryService : discoveryServicesAll) {
    addDiscoveryServiceActivated(discoveryService);
  }
}
