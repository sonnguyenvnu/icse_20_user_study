public void queryServerSearch(final String query,final boolean allowUsername,final boolean allowChats,final boolean allowBots,final boolean allowSelf,final int channelId,final int type){
  if (reqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true);
    reqId=0;
  }
  if (channelReqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(channelReqId,true);
    channelReqId=0;
  }
  if (query == null) {
    groupSearch.clear();
    groupSearchMap.clear();
    globalSearch.clear();
    globalSearchMap.clear();
    localServerSearch.clear();
    lastReqId=0;
    channelLastReqId=0;
    delegate.onDataSetChanged();
    return;
  }
  if (query.length() > 0) {
    if (channelId != 0) {
      TLRPC.TL_channels_getParticipants req=new TLRPC.TL_channels_getParticipants();
      if (type == ChatUsersActivity.TYPE_ADMIN) {
        req.filter=new TLRPC.TL_channelParticipantsAdmins();
      }
 else       if (type == ChatUsersActivity.TYPE_KICKED) {
        req.filter=new TLRPC.TL_channelParticipantsBanned();
      }
 else       if (type == ChatUsersActivity.TYPE_BANNED) {
        req.filter=new TLRPC.TL_channelParticipantsKicked();
      }
 else {
        req.filter=new TLRPC.TL_channelParticipantsSearch();
      }
      req.filter.q=query;
      req.limit=50;
      req.offset=0;
      req.channel=MessagesController.getInstance(currentAccount).getInputChannel(channelId);
      final int currentReqId=++channelLastReqId;
      channelReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (currentReqId == channelLastReqId) {
          if (error == null) {
            TLRPC.TL_channels_channelParticipants res=(TLRPC.TL_channels_channelParticipants)response;
            lastFoundChannel=query.toLowerCase();
            MessagesController.getInstance(currentAccount).putUsers(res.users,false);
            groupSearch.clear();
            groupSearchMap.clear();
            groupSearch.addAll(res.participants);
            int currentUserId=UserConfig.getInstance(currentAccount).getClientUserId();
            for (int a=0, N=res.participants.size(); a < N; a++) {
              TLRPC.ChannelParticipant participant=res.participants.get(a);
              if (!allowSelf && participant.user_id == currentUserId) {
                groupSearch.remove(participant);
                continue;
              }
              groupSearchMap.put(participant.user_id,participant);
            }
            if (localSearchResults != null) {
              mergeResults(localSearchResults);
            }
            delegate.onDataSetChanged();
          }
        }
        channelReqId=0;
      }
),ConnectionsManager.RequestFlagFailOnServerErrors);
    }
 else {
      lastFoundChannel=query.toLowerCase();
    }
  }
 else {
    groupSearch.clear();
    groupSearchMap.clear();
    channelLastReqId=0;
    delegate.onDataSetChanged();
  }
  if (allowUsername) {
    if (query.length() > 0) {
      TLRPC.TL_contacts_search req=new TLRPC.TL_contacts_search();
      req.q=query;
      req.limit=50;
      final int currentReqId=++lastReqId;
      reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (currentReqId == lastReqId) {
          if (error == null) {
            TLRPC.TL_contacts_found res=(TLRPC.TL_contacts_found)response;
            globalSearch.clear();
            globalSearchMap.clear();
            localServerSearch.clear();
            MessagesController.getInstance(currentAccount).putChats(res.chats,false);
            MessagesController.getInstance(currentAccount).putUsers(res.users,false);
            MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
            SparseArray<TLRPC.Chat> chatsMap=new SparseArray<>();
            SparseArray<TLRPC.User> usersMap=new SparseArray<>();
            for (int a=0; a < res.chats.size(); a++) {
              TLRPC.Chat chat=res.chats.get(a);
              chatsMap.put(chat.id,chat);
            }
            for (int a=0; a < res.users.size(); a++) {
              TLRPC.User user=res.users.get(a);
              usersMap.put(user.id,user);
            }
            for (int b=0; b < 2; b++) {
              ArrayList<TLRPC.Peer> arrayList;
              if (b == 0) {
                if (!allResultsAreGlobal) {
                  continue;
                }
                arrayList=res.my_results;
              }
 else {
                arrayList=res.results;
              }
              for (int a=0; a < arrayList.size(); a++) {
                TLRPC.Peer peer=arrayList.get(a);
                TLRPC.User user=null;
                TLRPC.Chat chat=null;
                if (peer.user_id != 0) {
                  user=usersMap.get(peer.user_id);
                }
 else                 if (peer.chat_id != 0) {
                  chat=chatsMap.get(peer.chat_id);
                }
 else                 if (peer.channel_id != 0) {
                  chat=chatsMap.get(peer.channel_id);
                }
                if (chat != null) {
                  if (!allowChats) {
                    continue;
                  }
                  globalSearch.add(chat);
                  globalSearchMap.put(-chat.id,chat);
                }
 else                 if (user != null) {
                  if (!allowBots && user.bot || !allowSelf && user.self) {
                    continue;
                  }
                  globalSearch.add(user);
                  globalSearchMap.put(user.id,user);
                }
              }
            }
            if (!allResultsAreGlobal) {
              for (int a=0; a < res.my_results.size(); a++) {
                TLRPC.Peer peer=res.my_results.get(a);
                TLRPC.User user=null;
                TLRPC.Chat chat=null;
                if (peer.user_id != 0) {
                  user=usersMap.get(peer.user_id);
                }
 else                 if (peer.chat_id != 0) {
                  chat=chatsMap.get(peer.chat_id);
                }
 else                 if (peer.channel_id != 0) {
                  chat=chatsMap.get(peer.channel_id);
                }
                if (chat != null) {
                  localServerSearch.add(chat);
                  globalSearchMap.put(-chat.id,chat);
                }
 else                 if (user != null) {
                  localServerSearch.add(user);
                  globalSearchMap.put(user.id,user);
                }
              }
            }
            lastFoundUsername=query.toLowerCase();
            if (localSearchResults != null) {
              mergeResults(localSearchResults);
            }
            mergeExcludeResults();
            delegate.onDataSetChanged();
          }
        }
        reqId=0;
      }
),ConnectionsManager.RequestFlagFailOnServerErrors);
    }
 else {
      globalSearch.clear();
      globalSearchMap.clear();
      localServerSearch.clear();
      lastReqId=0;
      delegate.onDataSetChanged();
    }
  }
}
