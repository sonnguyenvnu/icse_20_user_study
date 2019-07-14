@GuardedBy("this") private void setPublicShuffleOrder(ShuffleOrder shuffleOrder,@Nullable Handler handler,@Nullable Runnable actionOnCompletion){
  Assertions.checkArgument((handler == null) == (actionOnCompletion == null));
  Handler playbackThreadHandler=this.playbackThreadHandler;
  if (playbackThreadHandler != null) {
    int size=getSize();
    if (shuffleOrder.getLength() != size) {
      shuffleOrder=shuffleOrder.cloneAndClear().cloneAndInsert(0,size);
    }
    playbackThreadHandler.obtainMessage(MSG_SET_SHUFFLE_ORDER,new MessageData<>(0,shuffleOrder,handler,actionOnCompletion)).sendToTarget();
  }
 else {
    this.shuffleOrder=shuffleOrder.getLength() > 0 ? shuffleOrder.cloneAndClear() : shuffleOrder;
    if (actionOnCompletion != null && handler != null) {
      handler.post(actionOnCompletion);
    }
  }
}
