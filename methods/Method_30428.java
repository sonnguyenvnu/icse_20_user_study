private Notification buildNotification(){
  MediaControllerCompat controller=mMediaSession.getController();
  MediaMetadataCompat metadata=controller.getMetadata();
  MediaDescriptionCompat description=metadata.getDescription();
  NotificationCompat.Builder builder=new NotificationCompat.Builder(mService,mChannelId).setContentTitle(description.getTitle()).setContentText(description.getSubtitle()).setSubText(description.getDescription());
  Bitmap largeIcon=description.getIconBitmap();
  if (largeIcon == null) {
    largeIcon=BitmapUtils.drawableToBitmap(AppCompatResources.getDrawable(mService,R.drawable.default_album_art));
  }
  builder.setLargeIcon(largeIcon).setContentIntent(controller.getSessionActivity()).setDeleteIntent(MediaButtonReceiver.makePendingIntent(mService,PlaybackStateCompat.ACTION_STOP)).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setSmallIcon(mSmallIconRes).setColor(ContextCompat.getColor(mService,mColorRes));
  builder.setShowWhen(false);
  boolean isPlaying=mIsPlaying.getAsBoolean();
  builder.setOngoing(isPlaying).addAction(new NotificationCompat.Action(R.drawable.skip_to_previous_icon_white_24dp,mService.getString(R.string.media_action_skip_to_previous),MediaButtonReceiver.makePendingIntent(mService,PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS))).addAction(new NotificationCompat.Action(isPlaying ? R.drawable.pause_icon_white_24dp : R.drawable.play_icon_white_24dp,mService.getString(isPlaying ? R.string.media_action_pause : R.string.media_action_play),MediaButtonReceiver.makePendingIntent(mService,isPlaying ? PlaybackStateCompat.ACTION_PAUSE : PlaybackStateCompat.ACTION_PLAY))).addAction(new NotificationCompat.Action(R.drawable.skip_to_next_icon_white_24dp,mService.getString(R.string.media_action_skip_to_next),MediaButtonReceiver.makePendingIntent(mService,PlaybackStateCompat.ACTION_SKIP_TO_NEXT))).setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mMediaSession.getSessionToken()).setShowActionsInCompactView(0,1,2).setShowCancelButton(true).setCancelButtonIntent(MediaButtonReceiver.makePendingIntent(mService,PlaybackStateCompat.ACTION_STOP)));
  return builder.build();
}
