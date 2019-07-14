private void setEndpointAndRelaunch(final @NonNull ApiEndpoint apiEndpoint){
  this.apiEndpointPreference.set(apiEndpoint.url());
  this.logout.execute();
  try {
    Thread.sleep(500L);
  }
 catch (  InterruptedException ignored) {
  }
  ProcessPhoenix.triggerRebirth(this);
}
