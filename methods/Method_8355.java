private void openRightsEdit2(int userId,int date,TLObject participant,TLRPC.TL_chatAdminRights adminRights,TLRPC.TL_chatBannedRights bannedRights,boolean canEditAdmin,int type,boolean removeFragment){
  ChatRightsEditActivity fragment=new ChatRightsEditActivity(userId,chatId,adminRights,defaultBannedRights,bannedRights,type,true,false);
  fragment.setDelegate((rights,rightsAdmin,rightsBanned) -> {
    if (type == 0) {
      for (int a=0; a < participants.size(); a++) {
        TLObject p=participants.get(a);
        if (p instanceof TLRPC.ChannelParticipant) {
          TLRPC.ChannelParticipant p2=(TLRPC.ChannelParticipant)p;
          if (p2.user_id == userId) {
            TLRPC.ChannelParticipant newPart;
            if (rights == 1) {
              newPart=new TLRPC.TL_channelParticipantAdmin();
            }
 else {
              newPart=new TLRPC.TL_channelParticipant();
            }
            newPart.admin_rights=rightsAdmin;
            newPart.banned_rights=rightsBanned;
            newPart.inviter_id=UserConfig.getInstance(currentAccount).getClientUserId();
            newPart.user_id=userId;
            newPart.date=date;
            participants.set(a,newPart);
            break;
          }
        }
 else         if (p instanceof TLRPC.ChatParticipant) {
          TLRPC.ChatParticipant chatParticipant=(TLRPC.ChatParticipant)p;
          TLRPC.ChatParticipant newParticipant;
          if (rights == 1) {
            newParticipant=new TLRPC.TL_chatParticipantAdmin();
          }
 else {
            newParticipant=new TLRPC.TL_chatParticipant();
          }
          newParticipant.user_id=chatParticipant.user_id;
          newParticipant.date=chatParticipant.date;
          newParticipant.inviter_id=chatParticipant.inviter_id;
          int index=info.participants.participants.indexOf(chatParticipant);
          if (index >= 0) {
            info.participants.participants.set(index,newParticipant);
          }
          loadChatParticipants(0,200);
        }
      }
    }
 else     if (type == 1) {
      if (rights == 0) {
        removeParticipants(userId);
      }
    }
  }
);
  presentFragment(fragment);
}
