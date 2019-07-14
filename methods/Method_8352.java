private void onDonePressed(){
  if (!ChatObject.isChannel(currentChat) && (currentType == TYPE_BANNED || currentType == TYPE_ADMIN && !isDefaultAdminRights())) {
    MessagesController.getInstance(currentAccount).convertToMegaGroup(getParentActivity(),chatId,param -> {
      chatId=param;
      currentChat=MessagesController.getInstance(currentAccount).getChat(param);
      onDonePressed();
    }
);
    return;
  }
  if (currentType == TYPE_ADMIN) {
    if (isChannel) {
      adminRights.pin_messages=adminRights.ban_users=false;
    }
 else {
      adminRights.post_messages=adminRights.edit_messages=false;
    }
    MessagesController.getInstance(currentAccount).setUserAdminRole(chatId,currentUser,adminRights,isChannel,getFragmentForAlert(1),isAddingNew);
    if (delegate != null) {
      delegate.didSetRights(adminRights.change_info || adminRights.post_messages || adminRights.edit_messages || adminRights.delete_messages || adminRights.ban_users || adminRights.invite_users || adminRights.pin_messages || adminRights.add_admins ? 1 : 0,adminRights,bannedRights);
    }
  }
 else   if (currentType == TYPE_BANNED) {
    MessagesController.getInstance(currentAccount).setUserBannedRole(chatId,currentUser,bannedRights,isChannel,getFragmentForAlert(1));
    int rights;
    if (bannedRights.send_messages || bannedRights.send_stickers || bannedRights.embed_links || bannedRights.send_media || bannedRights.send_gifs || bannedRights.send_games || bannedRights.send_inline) {
      rights=1;
    }
 else {
      bannedRights.until_date=0;
      rights=2;
    }
    if (delegate != null) {
      delegate.didSetRights(rights,adminRights,bannedRights);
    }
  }
  finishFragment();
}
