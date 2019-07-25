private static void update(int[] appWidgetIds,AppWidgetManager appWidgetManager,Context context){
  SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
  boolean enabled=prefs.getBoolean("enabled",false);
  try {
    try {
      Intent intent=new Intent(enabled ? WidgetAdmin.INTENT_OFF : WidgetAdmin.INTENT_ON);
      intent.setPackage(context.getPackageName());
      PendingIntent pi=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
      for (      int id : appWidgetIds) {
        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widgetmain);
        views.setOnClickPendingIntent(R.id.ivEnabled,pi);
        views.setImageViewResource(R.id.ivEnabled,enabled ? R.drawable.ic_security_color_24dp : R.drawable.ic_security_white_24dp_60);
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
