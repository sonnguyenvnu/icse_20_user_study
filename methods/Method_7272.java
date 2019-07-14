void processorFinished(){
  if (mCompatQueue != null) {
synchronized (mCompatQueue) {
      mCurProcessor=null;
      if (mCompatQueue != null && mCompatQueue.size() > 0) {
        ensureProcessorRunningLocked(false);
      }
 else       if (!mDestroyed) {
        mCompatWorkEnqueuer.serviceProcessingFinished();
      }
    }
  }
}
