private void openRightsEdit(int user_id,TLObject participant,TLRPC.TL_chatAdminRights adminRights,TLRPC.TL_chatBannedRights bannedRights,boolean canEditAdmin,int type,boolean removeFragment){
  ChatRightsEditActivity fragment=new ChatRightsEditActivity(user_id,chatId,adminRights,defaultBannedRights,bannedRights,type,canEditAdmin,participant == null);
  fragment.setDelegate((rights,rightsAdmin,rightsBanned) -> {
    if (participant instanceof TLRPC.ChannelParticipant) {
      TLRPC.ChannelParticipant channelParticipant=(TLRPC.ChannelParticipant)participant;
      channelParticipant.admin_rights=rightsAdmin;
      channelParticipant.banned_rights=rightsBanned;
    }
    if (removeFragment) {
      removeSelfFromStack();
    }
  }
);
  presentFragment(fragment,removeFragment);
}
