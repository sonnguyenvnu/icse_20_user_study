private Runnable getMaybeAcquireStateAndReleaseTreeRunnable(final ComponentTreeHolder holder){
  return new Runnable(){
    @Override public void run(){
      maybeAcquireStateAndReleaseTree(holder);
    }
  }
;
}
