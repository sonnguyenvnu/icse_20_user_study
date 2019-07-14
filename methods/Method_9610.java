@SuppressWarnings("unchecked") @Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.messagesDeleted) {
    if (currentMessageObject == null) {
      return;
    }
    int channelId=(Integer)args[1];
    if (channelId != 0) {
      return;
    }
    ArrayList<Integer> markAsDeletedMessages=(ArrayList<Integer>)args[0];
    if (markAsDeletedMessages.contains(currentMessageObject.getId())) {
      if (isVideo && !videoWatchedOneTime) {
        closeVideoAfterWatch=true;
      }
 else {
        closePhoto(true,true);
      }
    }
  }
 else   if (id == NotificationCenter.didCreatedNewDeleteTask) {
    if (currentMessageObject == null || secretDeleteTimer == null) {
      return;
    }
    SparseArray<ArrayList<Long>> mids=(SparseArray<ArrayList<Long>>)args[0];
    for (int i=0; i < mids.size(); i++) {
      int key=mids.keyAt(i);
      ArrayList<Long> arr=mids.get(key);
      for (int a=0; a < arr.size(); a++) {
        long mid=arr.get(a);
        if (a == 0) {
          int channelId=(int)(mid >> 32);
          if (channelId < 0) {
            channelId=0;
          }
          if (channelId != currentChannelId) {
            return;
          }
        }
        if (currentMessageObject.getId() == mid) {
          currentMessageObject.messageOwner.destroyTime=key;
          secretDeleteTimer.invalidate();
          return;
        }
      }
    }
  }
 else   if (id == NotificationCenter.updateMessageMedia) {
    TLRPC.Message message=(TLRPC.Message)args[0];
    if (currentMessageObject.getId() == message.id) {
      if (isVideo && !videoWatchedOneTime) {
        closeVideoAfterWatch=true;
      }
 else {
        closePhoto(true,true);
      }
    }
  }
}
