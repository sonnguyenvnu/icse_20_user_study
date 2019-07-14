private void scheduleNextWork(long thisOperationStartTimeMs,long intervalMs){
  handler.removeMessages(MSG_DO_SOME_WORK);
  handler.sendEmptyMessageAtTime(MSG_DO_SOME_WORK,thisOperationStartTimeMs + intervalMs);
}
