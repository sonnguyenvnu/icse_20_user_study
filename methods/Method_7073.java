private void updatePlaybackState(long seekTo){
  if (Build.VERSION.SDK_INT < 21) {
    return;
  }
  boolean isPlaying=!MediaController.getInstance().isMessagePaused();
  if (MediaController.getInstance().isDownloadingCurrentMessage()) {
    playbackState.setState(PlaybackState.STATE_BUFFERING,0,1).setActions(0);
  }
 else {
    playbackState.setState(isPlaying ? PlaybackState.STATE_PLAYING : PlaybackState.STATE_PAUSED,seekTo,isPlaying ? 1 : 0).setActions(PlaybackState.ACTION_PLAY_PAUSE | PlaybackState.ACTION_PLAY | PlaybackState.ACTION_PAUSE | PlaybackState.ACTION_SEEK_TO | PlaybackState.ACTION_SKIP_TO_PREVIOUS | PlaybackState.ACTION_SKIP_TO_NEXT);
  }
  mediaSession.setPlaybackState(playbackState.build());
}
