@SuppressWarnings("unchecked") @Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.didReceiveNewMessages) {
    long did=(Long)args[0];
    if (!isSharingLocation(did)) {
      return;
    }
    ArrayList<TLRPC.Message> messages=locationsCache.get(did);
    if (messages == null) {
      return;
    }
    ArrayList<MessageObject> arr=(ArrayList<MessageObject>)args[1];
    boolean added=false;
    for (int a=0; a < arr.size(); a++) {
      MessageObject messageObject=arr.get(a);
      if (messageObject.isLiveLocation()) {
        added=true;
        boolean replaced=false;
        for (int b=0; b < messages.size(); b++) {
          if (messages.get(b).from_id == messageObject.messageOwner.from_id) {
            replaced=true;
            messages.set(b,messageObject.messageOwner);
            break;
          }
        }
        if (!replaced) {
          messages.add(messageObject.messageOwner);
        }
      }
    }
    if (added) {
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsCacheChanged,did,currentAccount);
    }
  }
 else   if (id == NotificationCenter.messagesDeleted) {
    if (!sharingLocationsUI.isEmpty()) {
      ArrayList<Integer> markAsDeletedMessages=(ArrayList<Integer>)args[0];
      int channelId=(Integer)args[1];
      ArrayList<Long> toRemove=null;
      for (int a=0; a < sharingLocationsUI.size(); a++) {
        SharingLocationInfo info=sharingLocationsUI.get(a);
        int messageChannelId=info.messageObject != null ? info.messageObject.getChannelId() : 0;
        if (channelId != messageChannelId) {
          continue;
        }
        if (markAsDeletedMessages.contains(info.mid)) {
          if (toRemove == null) {
            toRemove=new ArrayList<>();
          }
          toRemove.add(info.did);
        }
      }
      if (toRemove != null) {
        for (int a=0; a < toRemove.size(); a++) {
          removeSharingLocation(toRemove.get(a));
        }
      }
    }
  }
 else   if (id == NotificationCenter.replaceMessagesObjects) {
    long did=(long)args[0];
    if (!isSharingLocation(did)) {
      return;
    }
    ArrayList<TLRPC.Message> messages=locationsCache.get(did);
    if (messages == null) {
      return;
    }
    boolean updated=false;
    ArrayList<MessageObject> messageObjects=(ArrayList<MessageObject>)args[1];
    for (int a=0; a < messageObjects.size(); a++) {
      MessageObject messageObject=messageObjects.get(a);
      for (int b=0; b < messages.size(); b++) {
        if (messages.get(b).from_id == messageObject.messageOwner.from_id) {
          if (!messageObject.isLiveLocation()) {
            messages.remove(b);
          }
 else {
            messages.set(b,messageObject.messageOwner);
          }
          updated=true;
          break;
        }
      }
    }
    if (updated) {
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsCacheChanged,did,currentAccount);
    }
  }
}
