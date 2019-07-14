void ensureProcessorRunningLocked(boolean reportStarted){
  if (mCurProcessor == null) {
    mCurProcessor=new CommandProcessor();
    if (mCompatWorkEnqueuer != null && reportStarted) {
      mCompatWorkEnqueuer.serviceProcessingStarted();
    }
    if (DEBUG)     Log.d(TAG,"Starting processor: " + mCurProcessor);
    mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
  }
}
