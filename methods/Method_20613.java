public void trackDiscovery(final @NonNull DiscoveryParams params,final boolean isOnboardingVisible){
  final Map<String,Object> props=KoalaUtils.discoveryParamsProperties(params);
  props.put("discover_onboarding_is_visible",isOnboardingVisible);
  this.client.track("Discover List View",props);
}
