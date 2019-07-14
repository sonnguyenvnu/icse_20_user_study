@Override public long getSupportedQueueNavigatorActions(Player player){
  if (player == null || player.getCurrentTimeline().isEmpty()) {
    return 0;
  }
  return PlaybackStateCompat.ACTION_SKIP_TO_NEXT | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
}
