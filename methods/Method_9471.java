private void checkBufferedProgress(float progress){
  if (!isStreaming || parentActivity == null || streamingAlertShown || videoPlayer == null || currentMessageObject == null) {
    return;
  }
  TLRPC.Document document=currentMessageObject.getDocument();
  if (document == null) {
    return;
  }
  int innerDuration=currentMessageObject.getDuration();
  if (innerDuration < 20) {
    return;
  }
  if (progress < 0.9f && (document.size * progress >= 5 * 1024 * 1024 || progress >= 0.5f && document.size >= 2 * 1024 * 1024) && Math.abs(SystemClock.elapsedRealtime() - startedPlayTime) >= 2000) {
    long duration=videoPlayer.getDuration();
    if (duration == C.TIME_UNSET) {
      Toast toast=Toast.makeText(parentActivity,LocaleController.getString("VideoDoesNotSupportStreaming",R.string.VideoDoesNotSupportStreaming),Toast.LENGTH_LONG);
      toast.show();
    }
    streamingAlertShown=true;
  }
}
