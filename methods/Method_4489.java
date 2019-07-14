private void enableRenderer(int rendererIndex,boolean wasRendererEnabled,int enabledRendererIndex) throws ExoPlaybackException {
  MediaPeriodHolder playingPeriodHolder=queue.getPlayingPeriod();
  Renderer renderer=renderers[rendererIndex];
  enabledRenderers[enabledRendererIndex]=renderer;
  if (renderer.getState() == Renderer.STATE_DISABLED) {
    TrackSelectorResult trackSelectorResult=playingPeriodHolder.getTrackSelectorResult();
    RendererConfiguration rendererConfiguration=trackSelectorResult.rendererConfigurations[rendererIndex];
    TrackSelection newSelection=trackSelectorResult.selections.get(rendererIndex);
    Format[] formats=getFormats(newSelection);
    boolean playing=playWhenReady && playbackInfo.playbackState == Player.STATE_READY;
    boolean joining=!wasRendererEnabled && playing;
    renderer.enable(rendererConfiguration,formats,playingPeriodHolder.sampleStreams[rendererIndex],rendererPositionUs,joining,playingPeriodHolder.getRendererOffset());
    mediaClock.onRendererEnabled(renderer);
    if (playing) {
      renderer.start();
    }
  }
}
