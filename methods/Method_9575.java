private void openRightsEdit(int action,int user_id,TLRPC.ChatParticipant participant,TLRPC.TL_chatAdminRights adminRights,TLRPC.TL_chatBannedRights bannedRights){
  ChatRightsEditActivity fragment=new ChatRightsEditActivity(user_id,chat_id,adminRights,currentChat.default_banned_rights,bannedRights,action,true,false);
  fragment.setDelegate((rights,rightsAdmin,rightsBanned) -> {
    if (action == 0) {
      if (participant instanceof TLRPC.TL_chatChannelParticipant) {
        TLRPC.TL_chatChannelParticipant channelParticipant1=((TLRPC.TL_chatChannelParticipant)participant);
        if (rights == 1) {
          channelParticipant1.channelParticipant=new TLRPC.TL_channelParticipantAdmin();
        }
 else {
          channelParticipant1.channelParticipant=new TLRPC.TL_channelParticipant();
        }
        channelParticipant1.channelParticipant.inviter_id=UserConfig.getInstance(currentAccount).getClientUserId();
        channelParticipant1.channelParticipant.user_id=participant.user_id;
        channelParticipant1.channelParticipant.date=participant.date;
        channelParticipant1.channelParticipant.banned_rights=rightsBanned;
        channelParticipant1.channelParticipant.admin_rights=rightsAdmin;
      }
 else       if (participant instanceof TLRPC.ChatParticipant) {
        TLRPC.ChatParticipant newParticipant;
        if (rights == 1) {
          newParticipant=new TLRPC.TL_chatParticipantAdmin();
        }
 else {
          newParticipant=new TLRPC.TL_chatParticipant();
        }
        newParticipant.user_id=participant.user_id;
        newParticipant.date=participant.date;
        newParticipant.inviter_id=participant.inviter_id;
        int index=chatInfo.participants.participants.indexOf(participant);
        if (index >= 0) {
          chatInfo.participants.participants.set(index,newParticipant);
        }
      }
    }
 else     if (action == 1) {
      if (rights == 0) {
        if (currentChat.megagroup && chatInfo != null && chatInfo.participants != null) {
          boolean changed=false;
          for (int a=0; a < chatInfo.participants.participants.size(); a++) {
            TLRPC.ChannelParticipant p=((TLRPC.TL_chatChannelParticipant)chatInfo.participants.participants.get(a)).channelParticipant;
            if (p.user_id == participant.user_id) {
              if (chatInfo != null) {
                chatInfo.participants_count--;
              }
              chatInfo.participants.participants.remove(a);
              changed=true;
              break;
            }
          }
          if (chatInfo != null && chatInfo.participants != null) {
            for (int a=0; a < chatInfo.participants.participants.size(); a++) {
              TLRPC.ChatParticipant p=chatInfo.participants.participants.get(a);
              if (p.user_id == participant.user_id) {
                chatInfo.participants.participants.remove(a);
                changed=true;
                break;
              }
            }
          }
          if (changed) {
            updateOnlineCount();
            updateRowsIds();
            listAdapter.notifyDataSetChanged();
          }
        }
      }
    }
  }
);
  presentFragment(fragment);
}
