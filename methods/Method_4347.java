private void ensureSynced(){
  long rendererClockPositionUs=rendererClock.getPositionUs();
  standaloneMediaClock.resetPosition(rendererClockPositionUs);
  PlaybackParameters playbackParameters=rendererClock.getPlaybackParameters();
  if (!playbackParameters.equals(standaloneMediaClock.getPlaybackParameters())) {
    standaloneMediaClock.setPlaybackParameters(playbackParameters);
    listener.onPlaybackParametersChanged(playbackParameters);
  }
}
