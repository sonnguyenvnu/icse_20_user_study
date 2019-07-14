private void checkProxyInfoInternal(boolean reset){
  if (reset && checkingProxyInfo) {
    checkingProxyInfo=false;
  }
  if (!reset && nextProxyInfoCheckTime > ConnectionsManager.getInstance(currentAccount).getCurrentTime() || checkingProxyInfo) {
    return;
  }
  if (checkingProxyInfoRequestId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(checkingProxyInfoRequestId,true);
    checkingProxyInfoRequestId=0;
  }
  SharedPreferences preferences=getGlobalMainSettings();
  boolean enabled=preferences.getBoolean("proxy_enabled",false);
  String proxyAddress=preferences.getString("proxy_ip","");
  String proxySecret=preferences.getString("proxy_secret","");
  int removeCurrent=0;
  if (proxyDialogId != 0 && proxyDialogAddress != null && !proxyDialogAddress.equals(proxyAddress + proxySecret)) {
    removeCurrent=1;
  }
  if (enabled && !TextUtils.isEmpty(proxyAddress) && !TextUtils.isEmpty(proxySecret)) {
    checkingProxyInfo=true;
    TLRPC.TL_help_getProxyData req=new TLRPC.TL_help_getProxyData();
    checkingProxyInfoRequestId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (checkingProxyInfoRequestId == 0) {
        return;
      }
      boolean noDialog=false;
      if (response instanceof TLRPC.TL_help_proxyDataEmpty) {
        TLRPC.TL_help_proxyDataEmpty res=(TLRPC.TL_help_proxyDataEmpty)response;
        nextProxyInfoCheckTime=res.expires;
        noDialog=true;
      }
 else       if (response instanceof TLRPC.TL_help_proxyDataPromo) {
        final TLRPC.TL_help_proxyDataPromo res=(TLRPC.TL_help_proxyDataPromo)response;
        final long did;
        if (res.peer.user_id != 0) {
          did=res.peer.user_id;
        }
 else         if (res.peer.chat_id != 0) {
          did=-res.peer.chat_id;
          for (int a=0; a < res.chats.size(); a++) {
            TLRPC.Chat chat=res.chats.get(a);
            if (chat.id == res.peer.chat_id) {
              if (chat.kicked || chat.restricted) {
                noDialog=true;
              }
              break;
            }
          }
        }
 else {
          did=-res.peer.channel_id;
          for (int a=0; a < res.chats.size(); a++) {
            TLRPC.Chat chat=res.chats.get(a);
            if (chat.id == res.peer.channel_id) {
              if (chat.kicked || chat.restricted) {
                noDialog=true;
              }
              break;
            }
          }
        }
        proxyDialogId=did;
        proxyDialogAddress=proxyAddress + proxySecret;
        getGlobalMainSettings().edit().putLong("proxy_dialog",proxyDialogId).putString("proxyDialogAddress",proxyDialogAddress).commit();
        nextProxyInfoCheckTime=res.expires;
        if (!noDialog) {
          AndroidUtilities.runOnUIThread(() -> {
            proxyDialog=dialogs_dict.get(did);
            if (proxyDialog != null) {
              checkingProxyInfo=false;
              sortDialogs(null);
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
            }
 else {
              final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
              final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
              for (int a=0; a < res.users.size(); a++) {
                TLRPC.User u=res.users.get(a);
                usersDict.put(u.id,u);
              }
              for (int a=0; a < res.chats.size(); a++) {
                TLRPC.Chat c=res.chats.get(a);
                chatsDict.put(c.id,c);
              }
              TLRPC.TL_messages_getPeerDialogs req1=new TLRPC.TL_messages_getPeerDialogs();
              TLRPC.TL_inputDialogPeer peer=new TLRPC.TL_inputDialogPeer();
              if (res.peer.user_id != 0) {
                peer.peer=new TLRPC.TL_inputPeerUser();
                peer.peer.user_id=res.peer.user_id;
                TLRPC.User user=usersDict.get(res.peer.user_id);
                if (user != null) {
                  peer.peer.access_hash=user.access_hash;
                }
              }
 else               if (res.peer.chat_id != 0) {
                peer.peer=new TLRPC.TL_inputPeerChat();
                peer.peer.chat_id=res.peer.chat_id;
                TLRPC.Chat chat=chatsDict.get(res.peer.chat_id);
                if (chat != null) {
                  peer.peer.access_hash=chat.access_hash;
                }
              }
 else {
                peer.peer=new TLRPC.TL_inputPeerChannel();
                peer.peer.channel_id=res.peer.channel_id;
                TLRPC.Chat chat=chatsDict.get(res.peer.channel_id);
                if (chat != null) {
                  peer.peer.access_hash=chat.access_hash;
                }
              }
              req1.peers.add(peer);
              ConnectionsManager.getInstance(currentAccount).sendRequest(req1,(response1,error1) -> {
                if (checkingProxyInfoRequestId == 0) {
                  return;
                }
                checkingProxyInfoRequestId=0;
                final TLRPC.TL_messages_peerDialogs res2=(TLRPC.TL_messages_peerDialogs)response1;
                if (res2 != null && !res2.dialogs.isEmpty()) {
                  MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
                  TLRPC.TL_messages_dialogs dialogs=new TLRPC.TL_messages_dialogs();
                  dialogs.chats=res2.chats;
                  dialogs.users=res2.users;
                  dialogs.dialogs=res2.dialogs;
                  dialogs.messages=res2.messages;
                  MessagesStorage.getInstance(currentAccount).putDialogs(dialogs,2);
                  AndroidUtilities.runOnUIThread(() -> {
                    putUsers(res.users,false);
                    putChats(res.chats,false);
                    putUsers(res2.users,false);
                    putChats(res2.chats,false);
                    proxyDialog=res2.dialogs.get(0);
                    proxyDialog.id=did;
                    proxyDialog.folder_id=0;
                    if (DialogObject.isChannel(proxyDialog)) {
                      channelsPts.put(-(int)proxyDialog.id,proxyDialog.pts);
                    }
                    Integer value=dialogs_read_inbox_max.get(proxyDialog.id);
                    if (value == null) {
                      value=0;
                    }
                    dialogs_read_inbox_max.put(proxyDialog.id,Math.max(value,proxyDialog.read_inbox_max_id));
                    value=dialogs_read_outbox_max.get(proxyDialog.id);
                    if (value == null) {
                      value=0;
                    }
                    dialogs_read_outbox_max.put(proxyDialog.id,Math.max(value,proxyDialog.read_outbox_max_id));
                    dialogs_dict.put(did,proxyDialog);
                    if (!res2.messages.isEmpty()) {
                      final SparseArray<TLRPC.User> usersDict1=new SparseArray<>();
                      final SparseArray<TLRPC.Chat> chatsDict1=new SparseArray<>();
                      for (int a=0; a < res2.users.size(); a++) {
                        TLRPC.User u=res2.users.get(a);
                        usersDict1.put(u.id,u);
                      }
                      for (int a=0; a < res2.chats.size(); a++) {
                        TLRPC.Chat c=res2.chats.get(a);
                        chatsDict1.put(c.id,c);
                      }
                      MessageObject messageObject=new MessageObject(currentAccount,res2.messages.get(0),usersDict1,chatsDict1,false);
                      dialogMessage.put(did,messageObject);
                      if (proxyDialog.last_message_date == 0) {
                        proxyDialog.last_message_date=messageObject.messageOwner.date;
                      }
                    }
                    sortDialogs(null);
                    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
                  }
);
                }
 else {
                  AndroidUtilities.runOnUIThread(() -> {
                    if (proxyDialog != null) {
                      int lowerId=(int)proxyDialog.id;
                      if (lowerId < 0) {
                        TLRPC.Chat chat=getChat(-lowerId);
                        if (ChatObject.isNotInChat(chat) || chat.restricted) {
                          removeDialog(proxyDialog);
                        }
                      }
 else {
                        removeDialog(proxyDialog);
                      }
                      proxyDialog=null;
                      sortDialogs(null);
                      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
                    }
                  }
);
                }
                checkingProxyInfo=false;
              }
);
            }
          }
);
        }
      }
 else {
        nextProxyInfoCheckTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60 * 60;
        noDialog=true;
      }
      if (noDialog) {
        proxyDialogId=0;
        getGlobalMainSettings().edit().putLong("proxy_dialog",proxyDialogId).remove("proxyDialogAddress").commit();
        checkingProxyInfoRequestId=0;
        checkingProxyInfo=false;
        AndroidUtilities.runOnUIThread(() -> {
          if (proxyDialog != null) {
            int lowerId=(int)proxyDialog.id;
            if (lowerId < 0) {
              TLRPC.Chat chat=getChat(-lowerId);
              if (ChatObject.isNotInChat(chat) || chat.restricted) {
                removeDialog(proxyDialog);
              }
            }
 else {
              removeDialog(proxyDialog);
            }
            proxyDialog=null;
            sortDialogs(null);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
          }
        }
);
      }
    }
);
  }
 else {
    removeCurrent=2;
  }
  if (removeCurrent != 0) {
    proxyDialogId=0;
    proxyDialogAddress=null;
    getGlobalMainSettings().edit().putLong("proxy_dialog",proxyDialogId).remove("proxyDialogAddress").commit();
    nextProxyInfoCheckTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60 * 60;
    if (removeCurrent == 2) {
      checkingProxyInfo=false;
      if (checkingProxyInfoRequestId != 0) {
        ConnectionsManager.getInstance(currentAccount).cancelRequest(checkingProxyInfoRequestId,true);
        checkingProxyInfoRequestId=0;
      }
    }
    AndroidUtilities.runOnUIThread(() -> {
      if (proxyDialog != null) {
        int lowerId=(int)proxyDialog.id;
        if (lowerId < 0) {
          TLRPC.Chat chat=getChat(-lowerId);
          if (ChatObject.isNotInChat(chat) || chat.restricted) {
            removeDialog(proxyDialog);
          }
        }
 else {
          removeDialog(proxyDialog);
        }
        proxyDialog=null;
        sortDialogs(null);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
      }
    }
);
  }
}
