/** 
 * Convenience method to check whether another service is working in background If a service is found working (by checking  {@link #handlerThread} for it's state)then we wait for an interval of 5 secs, before checking on it again. Be advised - this method is not sure to start a new service, especially when app has been closed as there are higher chances for android system to GC the thread when it is running low on memory
 */
public static synchronized void runService(final Context context,final Intent intent){
switch (pendingIntents.size()) {
case 0:
    context.startService(intent);
  break;
case 1:
pendingIntents.add(intent);
postWaiting(context);
break;
case 2:
pendingIntents.add(intent);
notificationManager.notify(NotificationConstants.WAIT_ID,builder.build());
break;
default :
pendingIntents.add(intent);
break;
}
}
