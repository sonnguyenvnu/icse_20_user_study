private void updatePlayButton(){
  controlsView.checkNeedHide();
  AndroidUtilities.cancelRunOnUIThread(progressRunnable);
  if (!videoPlayer.isPlaying()) {
    if (isCompleted) {
      playButton.setImageResource(isInline ? R.drawable.ic_againinline : R.drawable.ic_again);
    }
 else {
      playButton.setImageResource(isInline ? R.drawable.ic_playinline : R.drawable.ic_play);
    }
  }
 else {
    playButton.setImageResource(isInline ? R.drawable.ic_pauseinline : R.drawable.ic_pause);
    AndroidUtilities.runOnUIThread(progressRunnable,500);
    checkAudioFocus();
  }
}
