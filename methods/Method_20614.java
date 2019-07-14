public void trackDiscoveryFilterSelected(final @NonNull DiscoveryParams params){
  this.client.track("Discover Modal Selected Filter",KoalaUtils.discoveryParamsProperties(params));
}
