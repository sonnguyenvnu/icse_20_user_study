private void onJobFinished(){
  long now=SystemClock.uptimeMillis();
  long when=0;
  boolean shouldEnqueue=false;
synchronized (this) {
    if (mJobState == JobState.RUNNING_AND_PENDING) {
      when=Math.max(mJobStartTime + mMinimumJobIntervalMs,now);
      shouldEnqueue=true;
      mJobSubmitTime=now;
      mJobState=JobState.QUEUED;
    }
 else {
      mJobState=JobState.IDLE;
    }
  }
  if (shouldEnqueue) {
    enqueueJob(when - now);
  }
}
