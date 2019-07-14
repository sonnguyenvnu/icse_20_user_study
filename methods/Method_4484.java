private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
  if (!queue.isLoading(mediaPeriod)) {
    return;
  }
  MediaPeriodHolder loadingPeriodHolder=queue.getLoadingPeriod();
  loadingPeriodHolder.handlePrepared(mediaClock.getPlaybackParameters().speed,playbackInfo.timeline);
  updateLoadControlTrackSelection(loadingPeriodHolder.getTrackGroups(),loadingPeriodHolder.getTrackSelectorResult());
  if (!queue.hasPlayingPeriod()) {
    MediaPeriodHolder playingPeriodHolder=queue.advancePlayingPeriod();
    resetRendererPosition(playingPeriodHolder.info.startPositionUs);
    updatePlayingPeriodRenderers(null);
  }
  maybeContinueLoading();
}
