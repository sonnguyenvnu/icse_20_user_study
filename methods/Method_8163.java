public void update(int mask){
  TLRPC.FileLocation photo=null;
  String newName=null;
  TLRPC.User currentUser=null;
  TLRPC.Chat currentChat=null;
  if (currentObject instanceof TLRPC.User) {
    currentUser=(TLRPC.User)currentObject;
    if (currentUser.photo != null) {
      photo=currentUser.photo.photo_small;
    }
  }
 else   if (currentObject instanceof TLRPC.Chat) {
    currentChat=(TLRPC.Chat)currentObject;
    if (currentChat.photo != null) {
      photo=currentChat.photo.photo_small;
    }
  }
  if (mask != 0) {
    boolean continueUpdate=false;
    if ((mask & MessagesController.UPDATE_MASK_AVATAR) != 0) {
      if (lastAvatar != null && photo == null || lastAvatar == null && photo != null && lastAvatar != null && photo != null && (lastAvatar.volume_id != photo.volume_id || lastAvatar.local_id != photo.local_id)) {
        continueUpdate=true;
      }
    }
    if (currentUser != null && !continueUpdate && (mask & MessagesController.UPDATE_MASK_STATUS) != 0) {
      int newStatus=0;
      if (currentUser.status != null) {
        newStatus=currentUser.status.expires;
      }
      if (newStatus != lastStatus) {
        continueUpdate=true;
      }
    }
    if (!continueUpdate && currentName == null && lastName != null && (mask & MessagesController.UPDATE_MASK_NAME) != 0) {
      if (currentUser != null) {
        newName=UserObject.getUserName(currentUser);
      }
 else {
        newName=currentChat.title;
      }
      if (!newName.equals(lastName)) {
        continueUpdate=true;
      }
    }
    if (!continueUpdate) {
      return;
    }
  }
  if (currentUser != null) {
    avatarDrawable.setInfo(currentUser);
    if (currentUser.status != null) {
      lastStatus=currentUser.status.expires;
    }
 else {
      lastStatus=0;
    }
  }
 else   if (currentChat != null) {
    avatarDrawable.setInfo(currentChat);
  }
 else   if (currentName != null) {
    avatarDrawable.setInfo(currentId,currentName.toString(),null,false);
  }
 else {
    avatarDrawable.setInfo(currentId,"#",null,false);
  }
  if (currentName != null) {
    lastName=null;
    nameTextView.setText(currentName);
  }
 else {
    if (currentUser != null) {
      lastName=newName == null ? UserObject.getUserName(currentUser) : newName;
    }
 else {
      lastName=newName == null ? currentChat.title : newName;
    }
    nameTextView.setText(lastName);
  }
  if (currentStatus != null) {
    statusTextView.setTextColor(statusColor);
    statusTextView.setText(currentStatus);
  }
 else   if (currentUser != null) {
    if (currentUser.bot) {
      statusTextView.setTextColor(statusColor);
      if (currentUser.bot_chat_history || adminTextView != null && adminTextView.getVisibility() == VISIBLE) {
        statusTextView.setText(LocaleController.getString("BotStatusRead",R.string.BotStatusRead));
      }
 else {
        statusTextView.setText(LocaleController.getString("BotStatusCantRead",R.string.BotStatusCantRead));
      }
    }
 else {
      if (currentUser.id == UserConfig.getInstance(currentAccount).getClientUserId() || currentUser.status != null && currentUser.status.expires > ConnectionsManager.getInstance(currentAccount).getCurrentTime() || MessagesController.getInstance(currentAccount).onlinePrivacy.containsKey(currentUser.id)) {
        statusTextView.setTextColor(statusOnlineColor);
        statusTextView.setText(LocaleController.getString("Online",R.string.Online));
      }
 else {
        statusTextView.setTextColor(statusColor);
        statusTextView.setText(LocaleController.formatUserStatus(currentAccount,currentUser));
      }
    }
  }
  if (imageView.getVisibility() == VISIBLE && currentDrawable == 0 || imageView.getVisibility() == GONE && currentDrawable != 0) {
    imageView.setVisibility(currentDrawable == 0 ? GONE : VISIBLE);
    imageView.setImageResource(currentDrawable);
  }
  lastAvatar=photo;
  if (currentUser != null) {
    avatarImageView.setImage(ImageLocation.getForUser(currentUser,false),"50_50",avatarDrawable,currentUser);
  }
 else   if (currentChat != null) {
    avatarImageView.setImage(ImageLocation.getForChat(currentChat,false),"50_50",avatarDrawable,currentChat);
  }
}
