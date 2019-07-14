private void loadSharingLocations(){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    final ArrayList<SharingLocationInfo> result=new ArrayList<>();
    final ArrayList<TLRPC.User> users=new ArrayList<>();
    final ArrayList<TLRPC.Chat> chats=new ArrayList<>();
    try {
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      ArrayList<Integer> chatsToLoad=new ArrayList<>();
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT uid, mid, date, period, message FROM sharing_locations WHERE 1");
      while (cursor.next()) {
        SharingLocationInfo info=new SharingLocationInfo();
        info.did=cursor.longValue(0);
        info.mid=cursor.intValue(1);
        info.stopTime=cursor.intValue(2);
        info.period=cursor.intValue(3);
        NativeByteBuffer data=cursor.byteBufferValue(4);
        if (data != null) {
          info.messageObject=new MessageObject(currentAccount,TLRPC.Message.TLdeserialize(data,data.readInt32(false),false),false);
          MessagesStorage.addUsersAndChatsFromMessage(info.messageObject.messageOwner,usersToLoad,chatsToLoad);
          data.reuse();
        }
        result.add(info);
        int lower_id=(int)info.did;
        int high_id=(int)(info.did >> 32);
        if (lower_id != 0) {
          if (lower_id < 0) {
            if (!chatsToLoad.contains(-lower_id)) {
              chatsToLoad.add(-lower_id);
            }
          }
 else {
            if (!usersToLoad.contains(lower_id)) {
              usersToLoad.add(lower_id);
            }
          }
        }
 else {
        }
      }
      cursor.dispose();
      if (!chatsToLoad.isEmpty()) {
        MessagesStorage.getInstance(currentAccount).getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
      }
      if (!usersToLoad.isEmpty()) {
        MessagesStorage.getInstance(currentAccount).getUsersInternal(TextUtils.join(",",usersToLoad),users);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (!result.isEmpty()) {
      AndroidUtilities.runOnUIThread(() -> {
        MessagesController.getInstance(currentAccount).putUsers(users,true);
        MessagesController.getInstance(currentAccount).putChats(chats,true);
        Utilities.stageQueue.postRunnable(() -> {
          sharingLocations.addAll(result);
          for (int a=0; a < sharingLocations.size(); a++) {
            SharingLocationInfo info=sharingLocations.get(a);
            sharingLocationsMap.put(info.did,info);
          }
          AndroidUtilities.runOnUIThread(() -> {
            sharingLocationsUI.addAll(result);
            for (int a=0; a < result.size(); a++) {
              SharingLocationInfo info=result.get(a);
              sharingLocationsMapUI.put(info.did,info);
            }
            startService();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged);
          }
);
        }
);
      }
);
    }
  }
);
}
