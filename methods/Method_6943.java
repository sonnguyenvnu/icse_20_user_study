public void sortDialogs(SparseArray<TLRPC.Chat> chatsDict){
  dialogsServerOnly.clear();
  dialogsCanAddUsers.clear();
  dialogsChannelsOnly.clear();
  dialogsGroupsOnly.clear();
  dialogsUsersOnly.clear();
  dialogsForward.clear();
  for (int a=0; a < dialogsByFolder.size(); a++) {
    ArrayList<TLRPC.Dialog> arrayList=dialogsByFolder.get(a);
    if (arrayList != null) {
      arrayList.clear();
    }
  }
  unreadUnmutedDialogs=0;
  boolean selfAdded=false;
  int selfId=UserConfig.getInstance(currentAccount).getClientUserId();
  Collections.sort(allDialogs,dialogComparator);
  isLeftProxyChannel=true;
  if (proxyDialog != null && proxyDialog.id < 0) {
    TLRPC.Chat chat=getChat(-(int)proxyDialog.id);
    if (chat != null && !chat.left) {
      isLeftProxyChannel=false;
    }
  }
  boolean countMessages=NotificationsController.getInstance(currentAccount).showBadgeMessages;
  for (int a=0, N=allDialogs.size(); a < N; a++) {
    TLRPC.Dialog d=allDialogs.get(a);
    int high_id=(int)(d.id >> 32);
    int lower_id=(int)d.id;
    if (d instanceof TLRPC.TL_dialog) {
      boolean canAddToForward=true;
      if (lower_id != 0 && high_id != 1) {
        dialogsServerOnly.add(d);
        if (DialogObject.isChannel(d)) {
          TLRPC.Chat chat=getChat(-lower_id);
          if (chat != null && (chat.megagroup && (chat.admin_rights != null && (chat.admin_rights.post_messages || chat.admin_rights.add_admins)) || chat.creator)) {
            dialogsCanAddUsers.add(d);
          }
          if (chat != null && chat.megagroup) {
            dialogsGroupsOnly.add(d);
          }
 else {
            dialogsChannelsOnly.add(d);
            canAddToForward=ChatObject.hasAdminRights(chat) && ChatObject.canPost(chat);
          }
        }
 else         if (lower_id < 0) {
          if (chatsDict != null) {
            TLRPC.Chat chat=chatsDict.get(-lower_id);
            if (chat != null && chat.migrated_to != null) {
              allDialogs.remove(a);
              a--;
              N--;
              continue;
            }
          }
          dialogsCanAddUsers.add(d);
          dialogsGroupsOnly.add(d);
        }
 else         if (lower_id > 0) {
          dialogsUsersOnly.add(d);
        }
      }
      if (canAddToForward) {
        if (lower_id == selfId) {
          dialogsForward.add(0,d);
          selfAdded=true;
        }
 else {
          dialogsForward.add(d);
        }
      }
    }
    if ((d.unread_count != 0 || d.unread_mark) && !isDialogMuted(d.id)) {
      unreadUnmutedDialogs++;
    }
    if (proxyDialog != null && d.id == proxyDialog.id && isLeftProxyChannel) {
      allDialogs.remove(a);
      a--;
      N--;
      continue;
    }
    addDialogToItsFolder(-1,d,countMessages);
  }
  if (proxyDialog != null && isLeftProxyChannel) {
    allDialogs.add(0,proxyDialog);
    addDialogToItsFolder(-2,proxyDialog,countMessages);
  }
  if (!selfAdded) {
    TLRPC.User user=UserConfig.getInstance(currentAccount).getCurrentUser();
    if (user != null) {
      TLRPC.Dialog dialog=new TLRPC.TL_dialog();
      dialog.id=user.id;
      dialog.notify_settings=new TLRPC.TL_peerNotifySettings();
      dialog.peer=new TLRPC.TL_peerUser();
      dialog.peer.user_id=user.id;
      dialogsForward.add(0,dialog);
    }
  }
  for (int a=0; a < dialogsByFolder.size(); a++) {
    int folderId=dialogsByFolder.keyAt(a);
    ArrayList<TLRPC.Dialog> dialogs=dialogsByFolder.valueAt(a);
    if (dialogs.isEmpty()) {
      dialogsByFolder.remove(folderId);
    }
  }
}
