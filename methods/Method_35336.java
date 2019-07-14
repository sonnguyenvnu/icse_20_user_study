private void setUpRemoteView(RemoteViews remoteView){
  remoteView.setImageViewResource(R.id.image_view_close,R.drawable.ic_remote_view_close);
  remoteView.setImageViewResource(R.id.image_view_play_last,R.drawable.ic_remote_view_play_last);
  remoteView.setImageViewResource(R.id.image_view_play_next,R.drawable.ic_remote_view_play_next);
  remoteView.setOnClickPendingIntent(R.id.button_close,getPendingIntent(ACTION_STOP_SERVICE));
  remoteView.setOnClickPendingIntent(R.id.button_play_last,getPendingIntent(ACTION_PLAY_LAST));
  remoteView.setOnClickPendingIntent(R.id.button_play_next,getPendingIntent(ACTION_PLAY_NEXT));
  remoteView.setOnClickPendingIntent(R.id.button_play_toggle,getPendingIntent(ACTION_PLAY_TOGGLE));
}
