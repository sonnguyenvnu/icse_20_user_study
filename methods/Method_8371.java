private void loadChatParticipants(int offset,int count,boolean reset){
  if (!ChatObject.isChannel(currentChat)) {
    loadingUsers=false;
    participants.clear();
    bots.clear();
    contacts.clear();
    participantsMap.clear();
    contactsMap.clear();
    botsMap.clear();
    if (type == TYPE_ADMIN) {
      if (info != null) {
        for (int a=0, size=info.participants.participants.size(); a < size; a++) {
          TLRPC.ChatParticipant participant=info.participants.participants.get(a);
          if (participant instanceof TLRPC.TL_chatParticipantCreator || participant instanceof TLRPC.TL_chatParticipantAdmin) {
            participants.add(participant);
          }
          participantsMap.put(participant.user_id,participant);
        }
      }
    }
 else     if (type == TYPE_USERS) {
      if (info != null) {
        int selfUserId=UserConfig.getInstance(currentAccount).clientUserId;
        for (int a=0, size=info.participants.participants.size(); a < size; a++) {
          TLRPC.ChatParticipant participant=info.participants.participants.get(a);
          if (selectType != 0 && participant.user_id == selfUserId) {
            continue;
          }
          if (selectType == 1) {
            if (ContactsController.getInstance(currentAccount).isContact(participant.user_id)) {
              contacts.add(participant);
              contactsMap.put(participant.user_id,participant);
            }
 else {
              participants.add(participant);
              participantsMap.put(participant.user_id,participant);
            }
          }
 else {
            if (ContactsController.getInstance(currentAccount).isContact(participant.user_id)) {
              contacts.add(participant);
              contactsMap.put(participant.user_id,participant);
            }
 else {
              TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(participant.user_id);
              if (user != null && user.bot) {
                bots.add(participant);
                botsMap.put(participant.user_id,participant);
              }
 else {
                participants.add(participant);
                participantsMap.put(participant.user_id,participant);
              }
            }
          }
        }
      }
    }
    if (listViewAdapter != null) {
      listViewAdapter.notifyDataSetChanged();
    }
    updateRows();
    if (listViewAdapter != null) {
      listViewAdapter.notifyDataSetChanged();
    }
  }
 else {
    loadingUsers=true;
    if (emptyView != null && !firstLoaded) {
      emptyView.showProgress();
    }
    if (listViewAdapter != null) {
      listViewAdapter.notifyDataSetChanged();
    }
    TLRPC.TL_channels_getParticipants req=new TLRPC.TL_channels_getParticipants();
    req.channel=MessagesController.getInstance(currentAccount).getInputChannel(chatId);
    if (type == TYPE_BANNED) {
      req.filter=new TLRPC.TL_channelParticipantsKicked();
    }
 else     if (type == TYPE_ADMIN) {
      req.filter=new TLRPC.TL_channelParticipantsAdmins();
    }
 else     if (type == TYPE_USERS) {
      if (info != null && info.participants_count <= 200 && currentChat != null && currentChat.megagroup) {
        req.filter=new TLRPC.TL_channelParticipantsRecent();
      }
 else {
        if (selectType == 1) {
          if (!contactsEndReached) {
            delayResults=2;
            req.filter=new TLRPC.TL_channelParticipantsContacts();
            contactsEndReached=true;
            loadChatParticipants(0,200,false);
          }
 else {
            req.filter=new TLRPC.TL_channelParticipantsRecent();
          }
        }
 else {
          if (!contactsEndReached) {
            delayResults=3;
            req.filter=new TLRPC.TL_channelParticipantsContacts();
            contactsEndReached=true;
            loadChatParticipants(0,200,false);
          }
 else           if (!botsEndReached) {
            req.filter=new TLRPC.TL_channelParticipantsBots();
            botsEndReached=true;
            loadChatParticipants(0,200,false);
          }
 else {
            req.filter=new TLRPC.TL_channelParticipantsRecent();
          }
        }
      }
    }
 else     if (type == TYPE_KICKED) {
      req.filter=new TLRPC.TL_channelParticipantsBanned();
    }
    req.filter.q="";
    req.offset=offset;
    req.limit=count;
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (error == null) {
        TLRPC.TL_channels_channelParticipants res=(TLRPC.TL_channels_channelParticipants)response;
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        int selfId=UserConfig.getInstance(currentAccount).getClientUserId();
        if (selectType != 0) {
          for (int a=0; a < res.participants.size(); a++) {
            if (res.participants.get(a).user_id == selfId) {
              res.participants.remove(a);
              break;
            }
          }
        }
        ArrayList<TLObject> objects;
        SparseArray<TLObject> map;
        if (type == TYPE_USERS) {
          delayResults--;
          if (req.filter instanceof TLRPC.TL_channelParticipantsContacts) {
            objects=contacts;
            map=contactsMap;
          }
 else           if (req.filter instanceof TLRPC.TL_channelParticipantsBots) {
            objects=bots;
            map=botsMap;
          }
 else {
            objects=participants;
            map=participantsMap;
          }
          if (delayResults <= 0) {
            if (emptyView != null) {
              emptyView.showTextView();
            }
          }
        }
 else {
          objects=participants;
          map=participantsMap;
          participantsMap.clear();
          if (emptyView != null) {
            emptyView.showTextView();
          }
        }
        objects.clear();
        objects.addAll(res.participants);
        for (int a=0, size=res.participants.size(); a < size; a++) {
          TLRPC.ChannelParticipant participant=res.participants.get(a);
          map.put(participant.user_id,participant);
        }
        if (type == TYPE_USERS) {
          for (int a=0, N=participants.size(); a < N; a++) {
            TLRPC.ChannelParticipant participant=(TLRPC.ChannelParticipant)participants.get(a);
            if (contactsMap.get(participant.user_id) != null || botsMap.get(participant.user_id) != null) {
              participants.remove(a);
              participantsMap.remove(participant.user_id);
              a--;
              N--;
            }
          }
        }
        try {
          if ((type == TYPE_BANNED || type == TYPE_KICKED || type == TYPE_USERS) && currentChat != null && currentChat.megagroup && info instanceof TLRPC.TL_channelFull && info.participants_count <= 200) {
            int currentTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
            Collections.sort(objects,(lhs,rhs) -> {
              TLRPC.ChannelParticipant p1=(TLRPC.ChannelParticipant)lhs;
              TLRPC.ChannelParticipant p2=(TLRPC.ChannelParticipant)rhs;
              TLRPC.User user1=MessagesController.getInstance(currentAccount).getUser(p1.user_id);
              TLRPC.User user2=MessagesController.getInstance(currentAccount).getUser(p2.user_id);
              int status1=0;
              int status2=0;
              if (user1 != null && user1.status != null) {
                if (user1.self) {
                  status1=currentTime + 50000;
                }
 else {
                  status1=user1.status.expires;
                }
              }
              if (user2 != null && user2.status != null) {
                if (user2.self) {
                  status2=currentTime + 50000;
                }
 else {
                  status2=user2.status.expires;
                }
              }
              if (status1 > 0 && status2 > 0) {
                if (status1 > status2) {
                  return 1;
                }
 else                 if (status1 < status2) {
                  return -1;
                }
                return 0;
              }
 else               if (status1 < 0 && status2 < 0) {
                if (status1 > status2) {
                  return 1;
                }
 else                 if (status1 < status2) {
                  return -1;
                }
                return 0;
              }
 else               if (status1 < 0 && status2 > 0 || status1 == 0 && status2 != 0) {
                return -1;
              }
 else               if (status2 < 0 && status1 > 0 || status2 == 0 && status1 != 0) {
                return 1;
              }
              return 0;
            }
);
          }
 else           if (type == TYPE_ADMIN) {
            Collections.sort(participants,(lhs,rhs) -> {
              int type1=getChannelAdminParticipantType(lhs);
              int type2=getChannelAdminParticipantType(rhs);
              if (type1 > type2) {
                return 1;
              }
 else               if (type1 < type2) {
                return -1;
              }
              return 0;
            }
);
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (type != TYPE_USERS || delayResults <= 0) {
        loadingUsers=false;
        firstLoaded=true;
      }
      updateRows();
      if (listViewAdapter != null) {
        listViewAdapter.notifyDataSetChanged();
      }
    }
));
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
}
