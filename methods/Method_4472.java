private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo){
  if (pendingMessageInfo.resolvedPeriodUid == null) {
    Pair<Object,Long> periodPosition=resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(),pendingMessageInfo.message.getWindowIndex(),C.msToUs(pendingMessageInfo.message.getPositionMs())),false);
    if (periodPosition == null) {
      return false;
    }
    pendingMessageInfo.setResolvedPosition(playbackInfo.timeline.getIndexOfPeriod(periodPosition.first),periodPosition.second,periodPosition.first);
  }
 else {
    int index=playbackInfo.timeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
    if (index == C.INDEX_UNSET) {
      return false;
    }
    pendingMessageInfo.resolvedPeriodIndex=index;
  }
  return true;
}
