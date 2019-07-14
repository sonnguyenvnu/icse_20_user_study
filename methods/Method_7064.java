@Override public void onLoadChildren(final String parentMediaId,final Result<List<MediaBrowser.MediaItem>> result){
  if (!chatsLoaded) {
    result.detach();
    if (loadingChats) {
      return;
    }
    loadingChats=true;
    final MessagesStorage messagesStorage=MessagesStorage.getInstance(currentAccount);
    messagesStorage.getStorageQueue().postRunnable(() -> {
      try {
        ArrayList<Integer> usersToLoad=new ArrayList<>();
        ArrayList<Integer> chatsToLoad=new ArrayList<>();
        SQLiteCursor cursor=messagesStorage.getDatabase().queryFinalized(String.format(Locale.US,"SELECT DISTINCT uid FROM media_v2 WHERE uid != 0 AND mid > 0 AND type = %d",DataQuery.MEDIA_MUSIC));
        while (cursor.next()) {
          int lower_part=(int)cursor.longValue(0);
          if (lower_part == 0) {
            continue;
          }
          dialogs.add(lower_part);
          if (lower_part > 0) {
            usersToLoad.add(lower_part);
          }
 else {
            chatsToLoad.add(-lower_part);
          }
        }
        cursor.dispose();
        if (!dialogs.isEmpty()) {
          String ids=TextUtils.join(",",dialogs);
          cursor=messagesStorage.getDatabase().queryFinalized(String.format(Locale.US,"SELECT uid, data, mid FROM media_v2 WHERE uid IN (%s) AND mid > 0 AND type = %d ORDER BY date DESC, mid DESC",ids,DataQuery.MEDIA_MUSIC));
          while (cursor.next()) {
            NativeByteBuffer data=cursor.byteBufferValue(1);
            if (data != null) {
              TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
              message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
              data.reuse();
              if (MessageObject.isMusicMessage(message)) {
                int did=cursor.intValue(0);
                message.id=cursor.intValue(2);
                message.dialog_id=did;
                ArrayList<MessageObject> arrayList=musicObjects.get(did);
                ArrayList<MediaSession.QueueItem> arrayList1=musicQueues.get(did);
                if (arrayList == null) {
                  arrayList=new ArrayList<>();
                  musicObjects.put(did,arrayList);
                  arrayList1=new ArrayList<>();
                  musicQueues.put(did,arrayList1);
                }
                MessageObject messageObject=new MessageObject(currentAccount,message,false);
                arrayList.add(0,messageObject);
                MediaDescription.Builder builder=new MediaDescription.Builder().setMediaId(did + "_" + arrayList.size());
                builder.setTitle(messageObject.getMusicTitle());
                builder.setSubtitle(messageObject.getMusicAuthor());
                arrayList1.add(0,new MediaSession.QueueItem(builder.build(),arrayList1.size()));
              }
            }
          }
          cursor.dispose();
          if (!usersToLoad.isEmpty()) {
            ArrayList<TLRPC.User> usersArrayList=new ArrayList<>();
            messagesStorage.getUsersInternal(TextUtils.join(",",usersToLoad),usersArrayList);
            for (int a=0; a < usersArrayList.size(); a++) {
              TLRPC.User user=usersArrayList.get(a);
              users.put(user.id,user);
            }
          }
          if (!chatsToLoad.isEmpty()) {
            ArrayList<TLRPC.Chat> chatsArrayList=new ArrayList<>();
            messagesStorage.getChatsInternal(TextUtils.join(",",chatsToLoad),chatsArrayList);
            for (int a=0; a < chatsArrayList.size(); a++) {
              TLRPC.Chat chat=chatsArrayList.get(a);
              chats.put(chat.id,chat);
            }
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      AndroidUtilities.runOnUIThread(() -> {
        chatsLoaded=true;
        loadingChats=false;
        loadChildrenImpl(parentMediaId,result);
        if (lastSelectedDialog == 0 && !dialogs.isEmpty()) {
          lastSelectedDialog=dialogs.get(0);
        }
        if (lastSelectedDialog != 0) {
          ArrayList<MessageObject> arrayList=musicObjects.get(lastSelectedDialog);
          ArrayList<MediaSession.QueueItem> arrayList1=musicQueues.get(lastSelectedDialog);
          if (arrayList != null && !arrayList.isEmpty()) {
            mediaSession.setQueue(arrayList1);
            if (lastSelectedDialog > 0) {
              TLRPC.User user=users.get(lastSelectedDialog);
              if (user != null) {
                mediaSession.setQueueTitle(ContactsController.formatName(user.first_name,user.last_name));
              }
 else {
                mediaSession.setQueueTitle("DELETED USER");
              }
            }
 else {
              TLRPC.Chat chat=chats.get(-lastSelectedDialog);
              if (chat != null) {
                mediaSession.setQueueTitle(chat.title);
              }
 else {
                mediaSession.setQueueTitle("DELETED CHAT");
              }
            }
            MessageObject messageObject=arrayList.get(0);
            MediaMetadata.Builder builder=new MediaMetadata.Builder();
            builder.putLong(MediaMetadata.METADATA_KEY_DURATION,messageObject.getDuration() * 1000);
            builder.putString(MediaMetadata.METADATA_KEY_ARTIST,messageObject.getMusicAuthor());
            builder.putString(MediaMetadata.METADATA_KEY_TITLE,messageObject.getMusicTitle());
            mediaSession.setMetadata(builder.build());
          }
        }
        updatePlaybackState(null);
      }
);
    }
);
  }
 else {
    loadChildrenImpl(parentMediaId,result);
  }
}
