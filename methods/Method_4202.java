@Override public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters){
  if (isInitialized() && !canApplyPlaybackParameters) {
    this.playbackParameters=PlaybackParameters.DEFAULT;
    return this.playbackParameters;
  }
  PlaybackParameters lastSetPlaybackParameters=afterDrainPlaybackParameters != null ? afterDrainPlaybackParameters : !playbackParametersCheckpoints.isEmpty() ? playbackParametersCheckpoints.getLast().playbackParameters : this.playbackParameters;
  if (!playbackParameters.equals(lastSetPlaybackParameters)) {
    if (isInitialized()) {
      afterDrainPlaybackParameters=playbackParameters;
    }
 else {
      this.playbackParameters=audioProcessorChain.applyPlaybackParameters(playbackParameters);
    }
  }
  return this.playbackParameters;
}
