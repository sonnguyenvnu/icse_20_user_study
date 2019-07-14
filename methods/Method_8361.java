private void updateParticipantWithRights(TLRPC.ChannelParticipant channelParticipant,TLRPC.TL_chatAdminRights rightsAdmin,TLRPC.TL_chatBannedRights rightsBanned,int user_id,boolean withDelegate){
  boolean delegateCalled=false;
  for (int a=0; a < 3; a++) {
    SparseArray<TLObject> map;
    if (a == 0) {
      map=contactsMap;
    }
 else     if (a == 1) {
      map=botsMap;
    }
 else {
      map=participantsMap;
    }
    TLObject p=map.get(channelParticipant.user_id);
    if (p instanceof TLRPC.ChannelParticipant) {
      channelParticipant=(TLRPC.ChannelParticipant)p;
      channelParticipant.admin_rights=rightsAdmin;
      channelParticipant.banned_rights=rightsBanned;
      if (withDelegate) {
        channelParticipant.promoted_by=UserConfig.getInstance(currentAccount).getClientUserId();
      }
    }
    if (withDelegate && p != null && !delegateCalled && delegate != null) {
      delegateCalled=true;
      delegate.didAddParticipantToList(user_id,p);
    }
  }
}
