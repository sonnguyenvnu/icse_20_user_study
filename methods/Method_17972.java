void updateStateSync(String componentKey,StateUpdate stateUpdate,String attribution){
synchronized (this) {
    if (mRoot == null) {
      return;
    }
    mStateHandler.queueStateUpdate(componentKey,stateUpdate,false);
  }
  LithoStats.incStateUpdateSync(1);
  final Looper looper=Looper.myLooper();
  if (looper == null) {
    Log.w(TAG,"You cannot update state synchronously from a thread without a looper, " + "using the default background layout thread instead");
synchronized (mUpdateStateSyncRunnableLock) {
      if (mUpdateStateSyncRunnable != null) {
        mLayoutThreadHandler.remove(mUpdateStateSyncRunnable);
      }
      mUpdateStateSyncRunnable=new UpdateStateSyncRunnable(attribution);
      String tag=EMPTY_STRING;
      if (mLayoutThreadHandler.isTracing()) {
        tag="updateStateSyncNoLooper " + attribution;
      }
      mLayoutThreadHandler.post(mUpdateStateSyncRunnable,tag);
    }
    return;
  }
  final WeakReference<LithoHandler> handlerWr=sSyncStateUpdatesHandler.get();
  LithoHandler handler=handlerWr != null ? handlerWr.get() : null;
  if (handler == null) {
    handler=new DefaultLithoHandler(looper);
    sSyncStateUpdatesHandler.set(new WeakReference<>(handler));
  }
synchronized (mUpdateStateSyncRunnableLock) {
    if (mUpdateStateSyncRunnable != null) {
      handler.remove(mUpdateStateSyncRunnable);
    }
    mUpdateStateSyncRunnable=new UpdateStateSyncRunnable(attribution);
    String tag=EMPTY_STRING;
    if (handler.isTracing()) {
      tag="updateStateSync " + attribution;
    }
    handler.post(mUpdateStateSyncRunnable,tag);
  }
}
