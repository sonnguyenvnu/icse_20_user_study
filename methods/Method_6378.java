public void loadHints(boolean cache){
  if (loading || !UserConfig.getInstance(currentAccount).suggestContacts) {
    return;
  }
  if (cache) {
    if (loaded) {
      return;
    }
    loading=true;
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      final ArrayList<TLRPC.TL_topPeer> hintsNew=new ArrayList<>();
      final ArrayList<TLRPC.TL_topPeer> inlineBotsNew=new ArrayList<>();
      final ArrayList<TLRPC.User> users=new ArrayList<>();
      final ArrayList<TLRPC.Chat> chats=new ArrayList<>();
      int selfUserId=UserConfig.getInstance(currentAccount).getClientUserId();
      try {
        ArrayList<Integer> usersToLoad=new ArrayList<>();
        ArrayList<Integer> chatsToLoad=new ArrayList<>();
        SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT did, type, rating FROM chat_hints WHERE 1 ORDER BY rating DESC");
        while (cursor.next()) {
          int did=cursor.intValue(0);
          if (did == selfUserId) {
            continue;
          }
          int type=cursor.intValue(1);
          TLRPC.TL_topPeer peer=new TLRPC.TL_topPeer();
          peer.rating=cursor.doubleValue(2);
          if (did > 0) {
            peer.peer=new TLRPC.TL_peerUser();
            peer.peer.user_id=did;
            usersToLoad.add(did);
          }
 else {
            peer.peer=new TLRPC.TL_peerChat();
            peer.peer.chat_id=-did;
            chatsToLoad.add(-did);
          }
          if (type == 0) {
            hintsNew.add(peer);
          }
 else           if (type == 1) {
            inlineBotsNew.add(peer);
          }
        }
        cursor.dispose();
        if (!usersToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getUsersInternal(TextUtils.join(",",usersToLoad),users);
        }
        if (!chatsToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
        }
        AndroidUtilities.runOnUIThread(() -> {
          MessagesController.getInstance(currentAccount).putUsers(users,true);
          MessagesController.getInstance(currentAccount).putChats(chats,true);
          loading=false;
          loaded=true;
          hints=hintsNew;
          inlineBots=inlineBotsNew;
          buildShortcuts();
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadHints);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadInlineHints);
          if (Math.abs(UserConfig.getInstance(currentAccount).lastHintsSyncTime - (int)(System.currentTimeMillis() / 1000)) >= 24 * 60 * 60) {
            loadHints(false);
          }
        }
);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
    loaded=true;
  }
 else {
    loading=true;
    TLRPC.TL_contacts_getTopPeers req=new TLRPC.TL_contacts_getTopPeers();
    req.hash=0;
    req.bots_pm=false;
    req.correspondents=true;
    req.groups=false;
    req.channels=false;
    req.bots_inline=true;
    req.offset=0;
    req.limit=20;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (response instanceof TLRPC.TL_contacts_topPeers) {
        AndroidUtilities.runOnUIThread(() -> {
          final TLRPC.TL_contacts_topPeers topPeers=(TLRPC.TL_contacts_topPeers)response;
          MessagesController.getInstance(currentAccount).putUsers(topPeers.users,false);
          MessagesController.getInstance(currentAccount).putChats(topPeers.chats,false);
          for (int a=0; a < topPeers.categories.size(); a++) {
            TLRPC.TL_topPeerCategoryPeers category=topPeers.categories.get(a);
            if (category.category instanceof TLRPC.TL_topPeerCategoryBotsInline) {
              inlineBots=category.peers;
              UserConfig.getInstance(currentAccount).botRatingLoadTime=(int)(System.currentTimeMillis() / 1000);
            }
 else {
              hints=category.peers;
              int selfUserId=UserConfig.getInstance(currentAccount).getClientUserId();
              for (int b=0; b < hints.size(); b++) {
                TLRPC.TL_topPeer topPeer=hints.get(b);
                if (topPeer.peer.user_id == selfUserId) {
                  hints.remove(b);
                  break;
                }
              }
              UserConfig.getInstance(currentAccount).ratingLoadTime=(int)(System.currentTimeMillis() / 1000);
            }
          }
          UserConfig.getInstance(currentAccount).saveConfig(false);
          buildShortcuts();
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadHints);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadInlineHints);
          MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
            try {
              MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM chat_hints WHERE 1").stepThis().dispose();
              MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(topPeers.users,topPeers.chats,false,false);
              SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO chat_hints VALUES(?, ?, ?, ?)");
              for (int a=0; a < topPeers.categories.size(); a++) {
                int type;
                TLRPC.TL_topPeerCategoryPeers category=topPeers.categories.get(a);
                if (category.category instanceof TLRPC.TL_topPeerCategoryBotsInline) {
                  type=1;
                }
 else {
                  type=0;
                }
                for (int b=0; b < category.peers.size(); b++) {
                  TLRPC.TL_topPeer peer=category.peers.get(b);
                  int did;
                  if (peer.peer instanceof TLRPC.TL_peerUser) {
                    did=peer.peer.user_id;
                  }
 else                   if (peer.peer instanceof TLRPC.TL_peerChat) {
                    did=-peer.peer.chat_id;
                  }
 else {
                    did=-peer.peer.channel_id;
                  }
                  state.requery();
                  state.bindInteger(1,did);
                  state.bindInteger(2,type);
                  state.bindDouble(3,peer.rating);
                  state.bindInteger(4,0);
                  state.step();
                }
              }
              state.dispose();
              MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
              AndroidUtilities.runOnUIThread(() -> {
                UserConfig.getInstance(currentAccount).suggestContacts=true;
                UserConfig.getInstance(currentAccount).lastHintsSyncTime=(int)(System.currentTimeMillis() / 1000);
                UserConfig.getInstance(currentAccount).saveConfig(false);
              }
);
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
);
        }
);
      }
 else       if (response instanceof TLRPC.TL_contacts_topPeersDisabled) {
        AndroidUtilities.runOnUIThread(() -> {
          UserConfig.getInstance(currentAccount).suggestContacts=false;
          UserConfig.getInstance(currentAccount).lastHintsSyncTime=(int)(System.currentTimeMillis() / 1000);
          UserConfig.getInstance(currentAccount).saveConfig(false);
          clearTopPeers();
        }
);
      }
    }
);
  }
}
