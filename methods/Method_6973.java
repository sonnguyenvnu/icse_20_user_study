public void resetDialogs(final TLRPC.messages_Dialogs dialogsRes,final int messagesCount,final int seq,final int newPts,final int date,final int qts,final LongSparseArray<TLRPC.Dialog> new_dialogs_dict,final LongSparseArray<MessageObject> new_dialogMessage,final TLRPC.Message lastMessage,final int dialogsCount){
  storageQueue.postRunnable(() -> {
    try {
      int maxPinnedNum=0;
      ArrayList<Integer> dids=new ArrayList<>();
      int totalPinnedCount=dialogsRes.dialogs.size() - dialogsCount;
      final LongSparseArray<Integer> oldPinnedDialogNums=new LongSparseArray<>();
      ArrayList<Long> oldPinnedOrder=new ArrayList<>();
      ArrayList<Long> orderArrayList=new ArrayList<>();
      for (int a=dialogsCount; a < dialogsRes.dialogs.size(); a++) {
        TLRPC.Dialog dialog=dialogsRes.dialogs.get(a);
        orderArrayList.add(dialog.id);
      }
      SQLiteCursor cursor=database.queryFinalized("SELECT did, pinned FROM dialogs WHERE 1");
      while (cursor.next()) {
        long did=cursor.longValue(0);
        int pinnedNum=cursor.intValue(1);
        int lower_id=(int)did;
        if (lower_id != 0) {
          dids.add(lower_id);
          if (pinnedNum > 0) {
            maxPinnedNum=Math.max(pinnedNum,maxPinnedNum);
            oldPinnedDialogNums.put(did,pinnedNum);
            oldPinnedOrder.add(did);
          }
        }
      }
      Collections.sort(oldPinnedOrder,(o1,o2) -> {
        Integer val1=oldPinnedDialogNums.get(o1);
        Integer val2=oldPinnedDialogNums.get(o2);
        if (val1 < val2) {
          return 1;
        }
 else         if (val1 > val2) {
          return -1;
        }
        return 0;
      }
);
      while (oldPinnedOrder.size() < totalPinnedCount) {
        oldPinnedOrder.add(0,0L);
      }
      cursor.dispose();
      String ids="(" + TextUtils.join(",",dids) + ")";
      database.beginTransaction();
      database.executeFast("DELETE FROM dialogs WHERE did IN " + ids).stepThis().dispose();
      database.executeFast("DELETE FROM messages WHERE uid IN " + ids).stepThis().dispose();
      database.executeFast("DELETE FROM polls WHERE 1").stepThis().dispose();
      database.executeFast("DELETE FROM bot_keyboard WHERE uid IN " + ids).stepThis().dispose();
      database.executeFast("DELETE FROM media_v2 WHERE uid IN " + ids).stepThis().dispose();
      database.executeFast("DELETE FROM messages_holes WHERE uid IN " + ids).stepThis().dispose();
      database.executeFast("DELETE FROM media_holes_v2 WHERE uid IN " + ids).stepThis().dispose();
      database.commitTransaction();
      for (int a=0; a < totalPinnedCount; a++) {
        TLRPC.Dialog dialog=dialogsRes.dialogs.get(dialogsCount + a);
        if (dialog instanceof TLRPC.TL_dialog && !dialog.pinned) {
          continue;
        }
        int oldIdx=oldPinnedOrder.indexOf(dialog.id);
        int newIdx=orderArrayList.indexOf(dialog.id);
        if (oldIdx != -1 && newIdx != -1) {
          if (oldIdx == newIdx) {
            Integer oldNum=oldPinnedDialogNums.get(dialog.id);
            if (oldNum != null) {
              dialog.pinnedNum=oldNum;
            }
          }
 else {
            long oldDid=oldPinnedOrder.get(newIdx);
            Integer oldNum=oldPinnedDialogNums.get(oldDid);
            if (oldNum != null) {
              dialog.pinnedNum=oldNum;
            }
          }
        }
        if (dialog.pinnedNum == 0) {
          dialog.pinnedNum=(totalPinnedCount - a) + maxPinnedNum;
        }
      }
      putDialogsInternal(dialogsRes,0);
      saveDiffParamsInternal(seq,newPts,date,qts);
      int totalDialogsLoadCount=UserConfig.getInstance(currentAccount).getTotalDialogsCount(0);
      int[] dialogsLoadOffset=UserConfig.getInstance(currentAccount).getDialogLoadOffsets(0);
      int dialogsLoadOffsetId;
      int dialogsLoadOffsetDate;
      int dialogsLoadOffsetChannelId=0;
      int dialogsLoadOffsetChatId=0;
      int dialogsLoadOffsetUserId=0;
      long dialogsLoadOffsetAccess=0;
      totalDialogsLoadCount+=dialogsRes.dialogs.size();
      dialogsLoadOffsetId=lastMessage.id;
      dialogsLoadOffsetDate=lastMessage.date;
      if (lastMessage.to_id.channel_id != 0) {
        dialogsLoadOffsetChannelId=lastMessage.to_id.channel_id;
        dialogsLoadOffsetChatId=0;
        dialogsLoadOffsetUserId=0;
        for (int a=0; a < dialogsRes.chats.size(); a++) {
          TLRPC.Chat chat=dialogsRes.chats.get(a);
          if (chat.id == dialogsLoadOffsetChannelId) {
            dialogsLoadOffsetAccess=chat.access_hash;
            break;
          }
        }
      }
 else       if (lastMessage.to_id.chat_id != 0) {
        dialogsLoadOffsetChatId=lastMessage.to_id.chat_id;
        dialogsLoadOffsetChannelId=0;
        dialogsLoadOffsetUserId=0;
        for (int a=0; a < dialogsRes.chats.size(); a++) {
          TLRPC.Chat chat=dialogsRes.chats.get(a);
          if (chat.id == dialogsLoadOffsetChatId) {
            dialogsLoadOffsetAccess=chat.access_hash;
            break;
          }
        }
      }
 else       if (lastMessage.to_id.user_id != 0) {
        dialogsLoadOffsetUserId=lastMessage.to_id.user_id;
        dialogsLoadOffsetChatId=0;
        dialogsLoadOffsetChannelId=0;
        for (int a=0; a < dialogsRes.users.size(); a++) {
          TLRPC.User user=dialogsRes.users.get(a);
          if (user.id == dialogsLoadOffsetUserId) {
            dialogsLoadOffsetAccess=user.access_hash;
            break;
          }
        }
      }
      for (int a=0; a < 2; a++) {
        UserConfig.getInstance(currentAccount).setDialogsLoadOffset(a,dialogsLoadOffsetId,dialogsLoadOffsetDate,dialogsLoadOffsetUserId,dialogsLoadOffsetChatId,dialogsLoadOffsetChannelId,dialogsLoadOffsetAccess);
        UserConfig.getInstance(currentAccount).setTotalDialogsCount(a,totalDialogsLoadCount);
      }
      UserConfig.getInstance(currentAccount).saveConfig(false);
      MessagesController.getInstance(currentAccount).completeDialogsReset(dialogsRes,messagesCount,seq,newPts,date,qts,new_dialogs_dict,new_dialogMessage,lastMessage);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
