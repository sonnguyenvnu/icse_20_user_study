public void overwriteChannel(final int channel_id,final TLRPC.TL_updates_channelDifferenceTooLong difference,final int newDialogType){
  storageQueue.postRunnable(() -> {
    try {
      boolean checkInvite=false;
      final long did=-channel_id;
      int pinned=0;
      SQLiteCursor cursor=database.queryFinalized("SELECT pinned FROM dialogs WHERE did = " + did);
      if (!cursor.next()) {
        if (newDialogType != 0) {
          checkInvite=true;
        }
      }
 else {
        pinned=cursor.intValue(0);
      }
      cursor.dispose();
      database.executeFast("DELETE FROM messages WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM bot_keyboard WHERE uid = " + did).stepThis().dispose();
      database.executeFast("UPDATE media_counts_v2 SET old = 1 WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM media_v2 WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM messages_holes WHERE uid = " + did).stepThis().dispose();
      database.executeFast("DELETE FROM media_holes_v2 WHERE uid = " + did).stepThis().dispose();
      DataQuery.getInstance(currentAccount).clearBotKeyboard(did,null);
      TLRPC.TL_messages_dialogs dialogs=new TLRPC.TL_messages_dialogs();
      dialogs.chats.addAll(difference.chats);
      dialogs.users.addAll(difference.users);
      dialogs.messages.addAll(difference.messages);
      TLRPC.Dialog dialog=difference.dialog;
      dialog.id=did;
      dialog.flags=1;
      dialog.notify_settings=null;
      dialog.pinned=pinned != 0;
      dialog.pinnedNum=pinned;
      dialogs.dialogs.add(dialog);
      putDialogsInternal(dialogs,0);
      updateDialogsWithDeletedMessages(new ArrayList<>(),null,false,channel_id);
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.removeAllMessagesFromDialog,did,true));
      if (checkInvite) {
        if (newDialogType == 1) {
          MessagesController.getInstance(currentAccount).checkChannelInviter(channel_id);
        }
 else {
          MessagesController.getInstance(currentAccount).generateJoinMessage(channel_id,false);
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
