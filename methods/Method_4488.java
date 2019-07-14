private void enableRenderers(boolean[] rendererWasEnabledFlags,int totalEnabledRendererCount) throws ExoPlaybackException {
  enabledRenderers=new Renderer[totalEnabledRendererCount];
  int enabledRendererCount=0;
  TrackSelectorResult trackSelectorResult=queue.getPlayingPeriod().getTrackSelectorResult();
  for (int i=0; i < renderers.length; i++) {
    if (!trackSelectorResult.isRendererEnabled(i)) {
      renderers[i].reset();
    }
  }
  for (int i=0; i < renderers.length; i++) {
    if (trackSelectorResult.isRendererEnabled(i)) {
      enableRenderer(i,rendererWasEnabledFlags[i],enabledRendererCount++);
    }
  }
}
