private void reselectTracksInternal() throws ExoPlaybackException {
  if (!queue.hasPlayingPeriod()) {
    return;
  }
  float playbackSpeed=mediaClock.getPlaybackParameters().speed;
  MediaPeriodHolder periodHolder=queue.getPlayingPeriod();
  MediaPeriodHolder readingPeriodHolder=queue.getReadingPeriod();
  boolean selectionsChangedForReadPeriod=true;
  TrackSelectorResult newTrackSelectorResult;
  while (true) {
    if (periodHolder == null || !periodHolder.prepared) {
      return;
    }
    newTrackSelectorResult=periodHolder.selectTracks(playbackSpeed,playbackInfo.timeline);
    if (newTrackSelectorResult != null) {
      break;
    }
    if (periodHolder == readingPeriodHolder) {
      selectionsChangedForReadPeriod=false;
    }
    periodHolder=periodHolder.getNext();
  }
  if (selectionsChangedForReadPeriod) {
    MediaPeriodHolder playingPeriodHolder=queue.getPlayingPeriod();
    boolean recreateStreams=queue.removeAfter(playingPeriodHolder);
    boolean[] streamResetFlags=new boolean[renderers.length];
    long periodPositionUs=playingPeriodHolder.applyTrackSelection(newTrackSelectorResult,playbackInfo.positionUs,recreateStreams,streamResetFlags);
    if (playbackInfo.playbackState != Player.STATE_ENDED && periodPositionUs != playbackInfo.positionUs) {
      playbackInfo=playbackInfo.copyWithNewPosition(playbackInfo.periodId,periodPositionUs,playbackInfo.contentPositionUs,getTotalBufferedDurationUs());
      playbackInfoUpdate.setPositionDiscontinuity(Player.DISCONTINUITY_REASON_INTERNAL);
      resetRendererPosition(periodPositionUs);
    }
    int enabledRendererCount=0;
    boolean[] rendererWasEnabledFlags=new boolean[renderers.length];
    for (int i=0; i < renderers.length; i++) {
      Renderer renderer=renderers[i];
      rendererWasEnabledFlags[i]=renderer.getState() != Renderer.STATE_DISABLED;
      SampleStream sampleStream=playingPeriodHolder.sampleStreams[i];
      if (sampleStream != null) {
        enabledRendererCount++;
      }
      if (rendererWasEnabledFlags[i]) {
        if (sampleStream != renderer.getStream()) {
          disableRenderer(renderer);
        }
 else         if (streamResetFlags[i]) {
          renderer.resetPosition(rendererPositionUs);
        }
      }
    }
    playbackInfo=playbackInfo.copyWithTrackInfo(playingPeriodHolder.getTrackGroups(),playingPeriodHolder.getTrackSelectorResult());
    enableRenderers(rendererWasEnabledFlags,enabledRendererCount);
  }
 else {
    queue.removeAfter(periodHolder);
    if (periodHolder.prepared) {
      long loadingPeriodPositionUs=Math.max(periodHolder.info.startPositionUs,periodHolder.toPeriodTime(rendererPositionUs));
      periodHolder.applyTrackSelection(newTrackSelectorResult,loadingPeriodPositionUs,false);
    }
  }
  handleLoadingMediaPeriodChanged(true);
  if (playbackInfo.playbackState != Player.STATE_ENDED) {
    maybeContinueLoading();
    updatePlaybackPositions();
    handler.sendEmptyMessage(MSG_DO_SOME_WORK);
  }
}
