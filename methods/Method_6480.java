protected void onFail(boolean thread,final int reason){
  cleanup();
  state=stateFailed;
  if (thread) {
    Utilities.stageQueue.postRunnable(() -> delegate.didFailedLoadingFile(FileLoadOperation.this,reason));
  }
 else {
    delegate.didFailedLoadingFile(FileLoadOperation.this,reason);
  }
}
