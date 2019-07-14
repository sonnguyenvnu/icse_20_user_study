@SuppressWarnings("ParameterNotNullable") private void updatePlayingPeriodRenderers(@Nullable MediaPeriodHolder oldPlayingPeriodHolder) throws ExoPlaybackException {
  MediaPeriodHolder newPlayingPeriodHolder=queue.getPlayingPeriod();
  if (newPlayingPeriodHolder == null || oldPlayingPeriodHolder == newPlayingPeriodHolder) {
    return;
  }
  int enabledRendererCount=0;
  boolean[] rendererWasEnabledFlags=new boolean[renderers.length];
  for (int i=0; i < renderers.length; i++) {
    Renderer renderer=renderers[i];
    rendererWasEnabledFlags[i]=renderer.getState() != Renderer.STATE_DISABLED;
    if (newPlayingPeriodHolder.getTrackSelectorResult().isRendererEnabled(i)) {
      enabledRendererCount++;
    }
    if (rendererWasEnabledFlags[i] && (!newPlayingPeriodHolder.getTrackSelectorResult().isRendererEnabled(i) || (renderer.isCurrentStreamFinal() && renderer.getStream() == oldPlayingPeriodHolder.sampleStreams[i]))) {
      disableRenderer(renderer);
    }
  }
  playbackInfo=playbackInfo.copyWithTrackInfo(newPlayingPeriodHolder.getTrackGroups(),newPlayingPeriodHolder.getTrackSelectorResult());
  enableRenderers(rendererWasEnabledFlags,enabledRendererCount);
}
