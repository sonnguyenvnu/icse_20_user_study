@Override public void triggered(@Nonnull final FeedCleanupTriggerEvent event){
  getLog().debug("Cleanup event triggered: {}",new Object[]{event});
  queue.add(event);
}
