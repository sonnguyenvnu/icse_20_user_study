private void updateOnlineCount(){
  onlineCount=0;
  int currentTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  sortedUsers.clear();
  if (chatInfo instanceof TLRPC.TL_chatFull || chatInfo instanceof TLRPC.TL_channelFull && chatInfo.participants_count <= 200 && chatInfo.participants != null) {
    for (int a=0; a < chatInfo.participants.participants.size(); a++) {
      TLRPC.ChatParticipant participant=chatInfo.participants.participants.get(a);
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(participant.user_id);
      if (user != null && user.status != null && (user.status.expires > currentTime || user.id == UserConfig.getInstance(currentAccount).getClientUserId()) && user.status.expires > 10000) {
        onlineCount++;
      }
      sortedUsers.add(a);
    }
    try {
      Collections.sort(sortedUsers,(lhs,rhs) -> {
        TLRPC.User user1=MessagesController.getInstance(currentAccount).getUser(chatInfo.participants.participants.get(rhs).user_id);
        TLRPC.User user2=MessagesController.getInstance(currentAccount).getUser(chatInfo.participants.participants.get(lhs).user_id);
        int status1=0;
        int status2=0;
        if (user1 != null) {
          if (user1.bot) {
            status1=-110;
          }
 else           if (user1.self) {
            status1=currentTime + 50000;
          }
 else           if (user1.status != null) {
            status1=user1.status.expires;
          }
        }
        if (user2 != null) {
          if (user2.bot) {
            status2=-110;
          }
 else           if (user2.self) {
            status2=currentTime + 50000;
          }
 else           if (user2.status != null) {
            status2=user2.status.expires;
          }
        }
        if (status1 > 0 && status2 > 0) {
          if (status1 > status2) {
            return 1;
          }
 else           if (status1 < status2) {
            return -1;
          }
          return 0;
        }
 else         if (status1 < 0 && status2 < 0) {
          if (status1 > status2) {
            return 1;
          }
 else           if (status1 < status2) {
            return -1;
          }
          return 0;
        }
 else         if (status1 < 0 && status2 > 0 || status1 == 0 && status2 != 0) {
          return -1;
        }
 else         if (status2 < 0 && status1 > 0 || status2 == 0 && status1 != 0) {
          return 1;
        }
        return 0;
      }
);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (listAdapter != null && membersStartRow > 0) {
      listAdapter.notifyItemRangeChanged(membersStartRow,sortedUsers.size());
    }
  }
 else   if (chatInfo instanceof TLRPC.TL_channelFull && chatInfo.participants_count > 200) {
    onlineCount=chatInfo.online_count;
  }
}
