private synchronized void maybeScheduleInactivityCheck(){
  if (!mInactivityCheckScheduled) {
    mInactivityCheckScheduled=true;
    mScheduledExecutorServiceForUiThread.schedule(mIsInactiveCheck,mInactivityCheckPollingTimeMs,TimeUnit.MILLISECONDS);
  }
}
