public void finishLastActivity(@Nullable Thread uncaughtExceptionThread){
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Finishing activities prior to killing the Process");
  boolean wait=false;
  for (  Activity activity : lastActivityManager.getLastActivities()) {
    final boolean isMainThread=uncaughtExceptionThread == activity.getMainLooper().getThread();
    final Runnable finisher=() -> {
      activity.finish();
      if (ACRA.DEV_LOGGING)       ACRA.log.d(LOG_TAG,"Finished " + activity.getClass());
    }
;
    if (isMainThread) {
      finisher.run();
    }
 else {
      wait=true;
      activity.runOnUiThread(finisher);
    }
  }
  if (wait) {
    lastActivityManager.waitForAllActivitiesDestroy(100);
  }
  lastActivityManager.clearLastActivities();
}
