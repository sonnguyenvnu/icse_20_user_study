private void onContentSourceInfoRefreshed(Timeline timeline,Object manifest){
  Assertions.checkArgument(timeline.getPeriodCount() == 1);
  contentTimeline=timeline;
  contentManifest=manifest;
  maybeUpdateSourceInfo();
}
