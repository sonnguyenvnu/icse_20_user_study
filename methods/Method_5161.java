@Override protected void onChildSourceInfoRefreshed(Void id,MediaSource mediaSource,Timeline timeline,@Nullable Object manifest){
  if (clippingError != null) {
    return;
  }
  this.manifest=manifest;
  refreshClippedTimeline(timeline);
}
