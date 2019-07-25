@Override public void triggered(FeedCleanupTriggerEvent event){
  this.provider.feedRemoved(event.getFeedId(),event.getCategoryName(),event.getFeedName());
  this.recorder.feedRemoved(event.getFeedId(),event.getCategoryName(),event.getFeedName());
}
