private long applySpeedup(long positionUs){
  @Nullable PlaybackParametersCheckpoint checkpoint=null;
  while (!playbackParametersCheckpoints.isEmpty() && positionUs >= playbackParametersCheckpoints.getFirst().positionUs) {
    checkpoint=playbackParametersCheckpoints.remove();
  }
  if (checkpoint != null) {
    playbackParameters=checkpoint.playbackParameters;
    playbackParametersPositionUs=checkpoint.positionUs;
    playbackParametersOffsetUs=checkpoint.mediaTimeUs - startMediaTimeUs;
  }
  if (playbackParameters.speed == 1f) {
    return positionUs + playbackParametersOffsetUs - playbackParametersPositionUs;
  }
  if (playbackParametersCheckpoints.isEmpty()) {
    return playbackParametersOffsetUs + audioProcessorChain.getMediaDuration(positionUs - playbackParametersPositionUs);
  }
  return playbackParametersOffsetUs + Util.getMediaDurationForPlayoutDuration(positionUs - playbackParametersPositionUs,playbackParameters.speed);
}
