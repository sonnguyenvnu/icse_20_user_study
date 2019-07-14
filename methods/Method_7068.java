private long getAvailableActions(){
  long actions=PlaybackState.ACTION_PLAY | PlaybackState.ACTION_PLAY_FROM_MEDIA_ID | PlaybackState.ACTION_PLAY_FROM_SEARCH;
  MessageObject playingMessageObject=MediaController.getInstance().getPlayingMessageObject();
  if (playingMessageObject != null) {
    if (!MediaController.getInstance().isMessagePaused()) {
      actions|=PlaybackState.ACTION_PAUSE;
    }
    actions|=PlaybackState.ACTION_SKIP_TO_PREVIOUS;
    actions|=PlaybackState.ACTION_SKIP_TO_NEXT;
  }
  return actions;
}
