@Override protected void onChildSourceInfoRefreshed(Integer id,MediaSource mediaSource,Timeline timeline,@Nullable Object manifest){
  if (mergeError == null) {
    mergeError=checkTimelineMerges(timeline);
  }
  if (mergeError != null) {
    return;
  }
  pendingTimelineSources.remove(mediaSource);
  timelines[id]=timeline;
  if (mediaSource == mediaSources[0]) {
    primaryManifest=manifest;
  }
  if (pendingTimelineSources.isEmpty()) {
    refreshSourceInfo(timelines[0],primaryManifest);
  }
}
