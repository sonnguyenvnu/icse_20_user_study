@Override public BackgroundCallback<T> getBackgroundProxy(final BackgroundCallback<T> callback){
  return new BackgroundCallback<T>(){
    @Override public void refresh(    int generation){
      sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(REFRESH,generation,null));
    }
    @Override public void updateRange(    int rangeStart,    int rangeEnd,    int extRangeStart,    int extRangeEnd,    int scrollHint){
      sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(UPDATE_RANGE,rangeStart,rangeEnd,extRangeStart,extRangeEnd,scrollHint,null));
    }
    @Override public void loadTile(    int position,    int scrollHint){
      sendMessage(SyncQueueItem.obtainMessage(LOAD_TILE,position,scrollHint));
    }
    @Override public void recycleTile(    TileList.Tile<T> tile){
      sendMessage(SyncQueueItem.obtainMessage(RECYCLE_TILE,0,tile));
    }
    private void sendMessage(    SyncQueueItem msg){
      mQueue.sendMessage(msg);
      maybeExecuteBackgroundRunnable();
    }
    private void sendMessageAtFrontOfQueue(    SyncQueueItem msg){
      mQueue.sendMessageAtFrontOfQueue(msg);
      maybeExecuteBackgroundRunnable();
    }
    private void maybeExecuteBackgroundRunnable(){
      if (mBackgroundRunning.compareAndSet(false,true)) {
        mExecutor.execute(mBackgroundRunnable);
      }
    }
  }
;
}
