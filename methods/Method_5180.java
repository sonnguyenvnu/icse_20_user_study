@GuardedBy("this") private void movePublicMediaSource(int currentIndex,int newIndex,@Nullable Handler handler,@Nullable Runnable actionOnCompletion){
  Assertions.checkArgument((handler == null) == (actionOnCompletion == null));
  mediaSourcesPublic.add(newIndex,mediaSourcesPublic.remove(currentIndex));
  if (playbackThreadHandler != null) {
    playbackThreadHandler.obtainMessage(MSG_MOVE,new MessageData<>(currentIndex,newIndex,handler,actionOnCompletion)).sendToTarget();
  }
 else   if (actionOnCompletion != null && handler != null) {
    handler.post(actionOnCompletion);
  }
}
