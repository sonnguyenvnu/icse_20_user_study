private void updateCounters(boolean hide){
  int canClearHistoryCount=0;
  int canDeleteCount=0;
  int canUnpinCount=0;
  int canArchiveCount=0;
  int canUnarchiveCount=0;
  canUnmuteCount=0;
  canMuteCount=0;
  canPinCount=0;
  canReadCount=0;
  canClearCacheCount=0;
  if (hide) {
    return;
  }
  ArrayList<Long> selectedDialogs=dialogsAdapter.getSelectedDialogs();
  int count=selectedDialogs.size();
  for (int a=0; a < count; a++) {
    TLRPC.Dialog dialog=getMessagesController().dialogs_dict.get(selectedDialogs.get(a));
    if (dialog == null) {
      continue;
    }
    long selectedDialog=dialog.id;
    boolean pinned=dialog.pinned;
    boolean hasUnread=dialog.unread_count != 0 || dialog.unread_mark;
    if (getMessagesController().isDialogMuted(selectedDialog)) {
      canUnmuteCount++;
    }
 else {
      canMuteCount++;
    }
    if (hasUnread) {
      canReadCount++;
    }
    if (folderId == 1) {
      canUnarchiveCount++;
    }
 else     if (selectedDialog != getUserConfig().getClientUserId() && selectedDialog != 777000 && !getMessagesController().isProxyDialog(selectedDialog,false)) {
      canArchiveCount++;
    }
    int lower_id=(int)selectedDialog;
    int high_id=(int)(selectedDialog >> 32);
    if (DialogObject.isChannel(dialog)) {
      final TLRPC.Chat chat=getMessagesController().getChat(-lower_id);
      CharSequence[] items;
      if (getMessagesController().isProxyDialog(dialog.id,true)) {
        canClearCacheCount++;
      }
 else {
        if (pinned) {
          canUnpinCount++;
        }
 else {
          canPinCount++;
        }
        if (chat != null && chat.megagroup) {
          if (TextUtils.isEmpty(chat.username)) {
            canClearHistoryCount++;
          }
 else {
            canClearCacheCount++;
          }
          canDeleteCount++;
        }
 else {
          canClearCacheCount++;
          canDeleteCount++;
        }
      }
    }
 else {
      final boolean isChat=lower_id < 0 && high_id != 1;
      TLRPC.User user;
      TLRPC.Chat chat=isChat ? getMessagesController().getChat(-lower_id) : null;
      if (lower_id == 0) {
        TLRPC.EncryptedChat encryptedChat=getMessagesController().getEncryptedChat(high_id);
        if (encryptedChat != null) {
          user=getMessagesController().getUser(encryptedChat.user_id);
        }
 else {
          user=new TLRPC.TL_userEmpty();
        }
      }
 else {
        user=!isChat && lower_id > 0 && high_id != 1 ? getMessagesController().getUser(lower_id) : null;
      }
      final boolean isBot=user != null && user.bot && !MessagesController.isSupportUser(user);
      if (pinned) {
        canUnpinCount++;
      }
 else {
        canPinCount++;
      }
      canClearHistoryCount++;
      canDeleteCount++;
    }
  }
  if (canDeleteCount != count) {
    deleteItem.setVisibility(View.GONE);
  }
 else {
    deleteItem.setVisibility(View.VISIBLE);
  }
  if (canClearCacheCount != 0 && canClearCacheCount != count || canClearHistoryCount != 0 && canClearHistoryCount != count) {
    clearItem.setVisibility(View.GONE);
  }
 else {
    clearItem.setVisibility(View.VISIBLE);
    if (canClearCacheCount != 0) {
      clearItem.setText(LocaleController.getString("ClearHistoryCache",R.string.ClearHistoryCache));
    }
 else {
      clearItem.setText(LocaleController.getString("ClearHistory",R.string.ClearHistory));
    }
  }
  if (canUnarchiveCount != 0) {
    archiveItem.setIcon(R.drawable.msg_unarchive);
    archiveItem.setContentDescription(LocaleController.getString("Unarchive",R.string.Unarchive));
  }
 else {
    archiveItem.setIcon(R.drawable.msg_archive);
    archiveItem.setContentDescription(LocaleController.getString("Archive",R.string.Archive));
    archiveItem.setEnabled(canArchiveCount != 0);
    archiveItem.setAlpha(canArchiveCount != 0 ? 1.0f : 0.5f);
  }
  if (canPinCount + canUnpinCount != count) {
    pinItem.setVisibility(View.GONE);
  }
 else {
    pinItem.setVisibility(View.VISIBLE);
  }
  if (canUnmuteCount != 0) {
    muteItem.setTextAndIcon(LocaleController.getString("ChatsUnmute",R.string.ChatsUnmute),R.drawable.msg_unmute);
  }
 else {
    muteItem.setTextAndIcon(LocaleController.getString("ChatsMute",R.string.ChatsMute),R.drawable.msg_mute);
  }
  if (canReadCount != 0) {
    readItem.setTextAndIcon(LocaleController.getString("MarkAsRead",R.string.MarkAsRead),R.drawable.msg_markread);
  }
 else {
    readItem.setTextAndIcon(LocaleController.getString("MarkAsUnread",R.string.MarkAsUnread),R.drawable.msg_markunread);
  }
  if (canPinCount != 0) {
    pinItem.setIcon(R.drawable.msg_pin);
    pinItem.setContentDescription(LocaleController.getString("PinToTop",R.string.PinToTop));
  }
 else {
    pinItem.setIcon(R.drawable.msg_unpin);
    pinItem.setContentDescription(LocaleController.getString("UnpinFromTop",R.string.UnpinFromTop));
  }
}
