private Notification buildNotification(){
  Intent notifyIntent=new Intent(this,TermuxActivity.class);
  notifyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notifyIntent,0);
  int sessionCount=mTerminalSessions.size();
  int taskCount=mBackgroundTasks.size();
  String contentText=sessionCount + " session" + (sessionCount == 1 ? "" : "s");
  if (taskCount > 0) {
    contentText+=", " + taskCount + " task" + (taskCount == 1 ? "" : "s");
  }
  final boolean wakeLockHeld=mWakeLock != null;
  if (wakeLockHeld)   contentText+=" (wake lock held)";
  Notification.Builder builder=new Notification.Builder(this);
  builder.setContentTitle(getText(R.string.application_name));
  builder.setContentText(contentText);
  builder.setSmallIcon(R.drawable.ic_service_notification);
  builder.setContentIntent(pendingIntent);
  builder.setOngoing(true);
  builder.setPriority((wakeLockHeld) ? Notification.PRIORITY_HIGH : Notification.PRIORITY_LOW);
  builder.setShowWhen(false);
  builder.setColor(0xFF607D8B);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    builder.setChannelId(NOTIFICATION_CHANNEL_ID);
  }
  Resources res=getResources();
  Intent exitIntent=new Intent(this,TermuxService.class).setAction(ACTION_STOP_SERVICE);
  builder.addAction(android.R.drawable.ic_delete,res.getString(R.string.notification_action_exit),PendingIntent.getService(this,0,exitIntent,0));
  String newWakeAction=wakeLockHeld ? ACTION_UNLOCK_WAKE : ACTION_LOCK_WAKE;
  Intent toggleWakeLockIntent=new Intent(this,TermuxService.class).setAction(newWakeAction);
  String actionTitle=res.getString(wakeLockHeld ? R.string.notification_action_wake_unlock : R.string.notification_action_wake_lock);
  int actionIcon=wakeLockHeld ? android.R.drawable.ic_lock_idle_lock : android.R.drawable.ic_lock_lock;
  builder.addAction(actionIcon,actionTitle,PendingIntent.getService(this,0,toggleWakeLockIntent,0));
  return builder.build();
}
