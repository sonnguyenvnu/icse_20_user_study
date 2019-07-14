protected void postOnResumed(Runnable runnable){
  if (isResumed()) {
    runnable.run();
  }
 else {
    mPendingRunnables.add(runnable);
  }
}
