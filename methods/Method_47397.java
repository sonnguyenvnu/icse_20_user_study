/** 
 * Helper method to  {@link #runService(Context,Intent)}Starts the wait watcher thread if not already started. Halting condition depends on the state of  {@link #handlerThread}
 */
private static synchronized void postWaiting(final Context context){
  waitingHandlerThread=new HandlerThread("service_startup_watcher");
  waitingHandlerThread.start();
  waitingHandler=new Handler(waitingHandlerThread.getLooper());
  notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  builder=new NotificationCompat.Builder(context,NotificationConstants.CHANNEL_NORMAL_ID).setContentTitle(context.getString(R.string.waiting_title)).setContentText(context.getString(R.string.waiting_content)).setAutoCancel(false).setSmallIcon(R.drawable.ic_all_inclusive_white_36dp).setProgress(0,0,true);
  NotificationConstants.setMetadata(context,builder,NotificationConstants.TYPE_NORMAL);
  Runnable runnable=new Runnable(){
    @Override public void run(){
      if (handlerThread == null || !handlerThread.isAlive()) {
        if (pendingIntents.size() == 0) {
          waitingHandler.removeCallbacks(this);
          waitingHandlerThread.quit();
          return;
        }
 else {
          if (pendingIntents.size() == 1) {
            notificationManager.cancel(NotificationConstants.WAIT_ID);
          }
          context.startService(pendingIntents.element());
        }
      }
      Log.d(getClass().getSimpleName(),"Processes in progress, delay the check");
      waitingHandler.postDelayed(this,1000);
    }
  }
;
  waitingHandler.postDelayed(runnable,0);
}
