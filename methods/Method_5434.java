@Override protected void onChildSourceInfoRefreshed(Void id,MediaSource mediaSource,Timeline timeline,@Nullable Object manifest){
  Timeline loopingTimeline=loopCount != Integer.MAX_VALUE ? new LoopingTimeline(timeline,loopCount) : new InfinitelyLoopingTimeline(timeline);
  refreshSourceInfo(loopingTimeline,manifest);
}
