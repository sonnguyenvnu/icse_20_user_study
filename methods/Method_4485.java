private void handlePlaybackParameters(PlaybackParameters playbackParameters) throws ExoPlaybackException {
  eventHandler.obtainMessage(MSG_PLAYBACK_PARAMETERS_CHANGED,playbackParameters).sendToTarget();
  updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
  for (  Renderer renderer : renderers) {
    if (renderer != null) {
      renderer.setOperatingRate(playbackParameters.speed);
    }
  }
}
