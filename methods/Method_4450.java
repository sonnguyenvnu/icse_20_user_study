private boolean shouldMaskPosition(){
  return playbackInfo.timeline.isEmpty() || pendingOperationAcks > 0;
}
