private static void update(int[] appWidgetIds,AppWidgetManager appWidgetManager,Context context){
  SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
  boolean lockdown=prefs.getBoolean("lockdown",false);
  try {
    try {
      Intent intent=new Intent(lockdown ? WidgetAdmin.INTENT_LOCKDOWN_OFF : WidgetAdmin.INTENT_LOCKDOWN_ON);
      intent.setPackage(context.getPackageName());
      PendingIntent pi=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
      for (      int id : appWidgetIds) {
        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widgetlockdown);
        views.setOnClickPendingIntent(R.id.ivEnabled,pi);
        views.setImageViewResource(R.id.ivEnabled,lockdown ? R.drawable.ic_lock_outline_white_24dp : R.drawable.ic_lock_open_white_24dp);
        appWidgetManager.updateAppWidget(id,views);
      }
    }
 catch (    Throwable ex) {
      Log.e(TAG,ex.toString() + "\n" + Log.getStackTraceString(ex));
    }
  }
 catch (  Throwable ex) {
    Log.e(TAG,ex.toString() + "\n" + Log.getStackTraceString(ex));
  }
}
