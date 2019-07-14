public void setListeners(RemoteViews view){
  PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_PREVIOUS),PendingIntent.FLAG_UPDATE_CURRENT);
  view.setOnClickPendingIntent(R.id.player_previous,pendingIntent);
  pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_CLOSE),PendingIntent.FLAG_UPDATE_CURRENT);
  view.setOnClickPendingIntent(R.id.player_close,pendingIntent);
  pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_PAUSE),PendingIntent.FLAG_UPDATE_CURRENT);
  view.setOnClickPendingIntent(R.id.player_pause,pendingIntent);
  pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_NEXT),PendingIntent.FLAG_UPDATE_CURRENT);
  view.setOnClickPendingIntent(R.id.player_next,pendingIntent);
  pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_PLAY),PendingIntent.FLAG_UPDATE_CURRENT);
  view.setOnClickPendingIntent(R.id.player_play,pendingIntent);
}
