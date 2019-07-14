private void maybeTriggerPendingMessages(long oldPeriodPositionUs,long newPeriodPositionUs) throws ExoPlaybackException {
  if (pendingMessages.isEmpty() || playbackInfo.periodId.isAd()) {
    return;
  }
  if (playbackInfo.startPositionUs == oldPeriodPositionUs) {
    oldPeriodPositionUs--;
  }
  int currentPeriodIndex=playbackInfo.timeline.getIndexOfPeriod(playbackInfo.periodId.periodUid);
  PendingMessageInfo previousInfo=nextPendingMessageIndex > 0 ? pendingMessages.get(nextPendingMessageIndex - 1) : null;
  while (previousInfo != null && (previousInfo.resolvedPeriodIndex > currentPeriodIndex || (previousInfo.resolvedPeriodIndex == currentPeriodIndex && previousInfo.resolvedPeriodTimeUs > oldPeriodPositionUs))) {
    nextPendingMessageIndex--;
    previousInfo=nextPendingMessageIndex > 0 ? pendingMessages.get(nextPendingMessageIndex - 1) : null;
  }
  PendingMessageInfo nextInfo=nextPendingMessageIndex < pendingMessages.size() ? pendingMessages.get(nextPendingMessageIndex) : null;
  while (nextInfo != null && nextInfo.resolvedPeriodUid != null && (nextInfo.resolvedPeriodIndex < currentPeriodIndex || (nextInfo.resolvedPeriodIndex == currentPeriodIndex && nextInfo.resolvedPeriodTimeUs <= oldPeriodPositionUs))) {
    nextPendingMessageIndex++;
    nextInfo=nextPendingMessageIndex < pendingMessages.size() ? pendingMessages.get(nextPendingMessageIndex) : null;
  }
  while (nextInfo != null && nextInfo.resolvedPeriodUid != null && nextInfo.resolvedPeriodIndex == currentPeriodIndex && nextInfo.resolvedPeriodTimeUs > oldPeriodPositionUs && nextInfo.resolvedPeriodTimeUs <= newPeriodPositionUs) {
    sendMessageToTarget(nextInfo.message);
    if (nextInfo.message.getDeleteAfterDelivery() || nextInfo.message.isCanceled()) {
      pendingMessages.remove(nextPendingMessageIndex);
    }
 else {
      nextPendingMessageIndex++;
    }
    nextInfo=nextPendingMessageIndex < pendingMessages.size() ? pendingMessages.get(nextPendingMessageIndex) : null;
  }
}
