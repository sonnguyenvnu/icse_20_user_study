@Override public MainThreadCallback<T> getMainThreadProxy(final MainThreadCallback<T> callback){
  return new MainThreadCallback<T>(){
    @Override public void updateItemCount(    int generation,    int itemCount){
      sendMessage(SyncQueueItem.obtainMessage(UPDATE_ITEM_COUNT,generation,itemCount));
    }
    @Override public void addTile(    int generation,    TileList.Tile<T> tile){
      sendMessage(SyncQueueItem.obtainMessage(ADD_TILE,generation,tile));
    }
    @Override public void removeTile(    int generation,    int position){
      sendMessage(SyncQueueItem.obtainMessage(REMOVE_TILE,generation,position));
    }
    private void sendMessage(    SyncQueueItem msg){
      mQueue.sendMessage(msg);
      mMainThreadHandler.post(mMainThreadRunnable);
    }
  }
;
}
