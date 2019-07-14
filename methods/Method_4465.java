private long seekToPeriodPosition(MediaPeriodId periodId,long periodPositionUs,boolean forceDisableRenderers) throws ExoPlaybackException {
  stopRenderers();
  rebuffering=false;
  setState(Player.STATE_BUFFERING);
  MediaPeriodHolder oldPlayingPeriodHolder=queue.getPlayingPeriod();
  MediaPeriodHolder newPlayingPeriodHolder=oldPlayingPeriodHolder;
  while (newPlayingPeriodHolder != null) {
    if (periodId.equals(newPlayingPeriodHolder.info.id) && newPlayingPeriodHolder.prepared) {
      queue.removeAfter(newPlayingPeriodHolder);
      break;
    }
    newPlayingPeriodHolder=queue.advancePlayingPeriod();
  }
  if (oldPlayingPeriodHolder != newPlayingPeriodHolder || forceDisableRenderers) {
    for (    Renderer renderer : enabledRenderers) {
      disableRenderer(renderer);
    }
    enabledRenderers=new Renderer[0];
    oldPlayingPeriodHolder=null;
  }
  if (newPlayingPeriodHolder != null) {
    updatePlayingPeriodRenderers(oldPlayingPeriodHolder);
    if (newPlayingPeriodHolder.hasEnabledTracks) {
      periodPositionUs=newPlayingPeriodHolder.mediaPeriod.seekToUs(periodPositionUs);
      newPlayingPeriodHolder.mediaPeriod.discardBuffer(periodPositionUs - backBufferDurationUs,retainBackBufferFromKeyframe);
    }
    resetRendererPosition(periodPositionUs);
    maybeContinueLoading();
  }
 else {
    queue.clear(true);
    playbackInfo=playbackInfo.copyWithTrackInfo(TrackGroupArray.EMPTY,emptyTrackSelectorResult);
    resetRendererPosition(periodPositionUs);
  }
  handleLoadingMediaPeriodChanged(false);
  handler.sendEmptyMessage(MSG_DO_SOME_WORK);
  return periodPositionUs;
}
