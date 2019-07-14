public void loadUnreadDialogs(){
  if (loadingUnreadDialogs || UserConfig.getInstance(currentAccount).unreadDialogsLoaded) {
    return;
  }
  loadingUnreadDialogs=true;
  TLRPC.TL_messages_getDialogUnreadMarks req=new TLRPC.TL_messages_getDialogUnreadMarks();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (response != null) {
      final TLRPC.Vector vector=(TLRPC.Vector)response;
      for (int a=0, size=vector.objects.size(); a < size; a++) {
        TLRPC.DialogPeer peer=(TLRPC.DialogPeer)vector.objects.get(a);
        if (peer instanceof TLRPC.TL_dialogPeer) {
          TLRPC.TL_dialogPeer dialogPeer=(TLRPC.TL_dialogPeer)peer;
          long did;
          if (dialogPeer.peer.user_id != 0) {
            if (dialogPeer.peer.user_id != 0) {
              did=dialogPeer.peer.user_id;
            }
 else             if (dialogPeer.peer.chat_id != 0) {
              did=-dialogPeer.peer.chat_id;
            }
 else {
              did=-dialogPeer.peer.channel_id;
            }
          }
 else {
            did=0;
          }
          MessagesStorage.getInstance(currentAccount).setDialogUnread(did,true);
          TLRPC.Dialog dialog=dialogs_dict.get(did);
          if (dialog != null && !dialog.unread_mark) {
            dialog.unread_mark=true;
            if (dialog.unread_count == 0 && !isDialogMuted(did)) {
              unreadUnmutedDialogs++;
            }
          }
        }
      }
      UserConfig.getInstance(currentAccount).unreadDialogsLoaded=true;
      UserConfig.getInstance(currentAccount).saveConfig(false);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_READ_DIALOG_MESSAGE);
      loadingUnreadDialogs=false;
    }
  }
));
}
