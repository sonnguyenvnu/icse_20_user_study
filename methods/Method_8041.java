public void update(int mask){
  if (currentObject == null) {
    return;
  }
  TLRPC.FileLocation photo=null;
  String newName=null;
  if (currentObject instanceof TLRPC.User) {
    TLRPC.User currentUser=(TLRPC.User)currentObject;
    if (currentUser.photo != null) {
      photo=currentUser.photo.photo_small;
    }
    if (mask != 0) {
      boolean continueUpdate=false;
      if ((mask & MessagesController.UPDATE_MASK_AVATAR) != 0) {
        if (lastAvatar != null && photo == null || lastAvatar == null && photo != null || lastAvatar != null && photo != null && (lastAvatar.volume_id != photo.volume_id || lastAvatar.local_id != photo.local_id)) {
          continueUpdate=true;
        }
      }
      if (currentUser != null && currentStatus == null && !continueUpdate && (mask & MessagesController.UPDATE_MASK_STATUS) != 0) {
        int newStatus=0;
        if (currentUser.status != null) {
          newStatus=currentUser.status.expires;
        }
        if (newStatus != lastStatus) {
          continueUpdate=true;
        }
      }
      if (!continueUpdate && currentName == null && lastName != null && (mask & MessagesController.UPDATE_MASK_NAME) != 0) {
        newName=UserObject.getUserName(currentUser);
        if (!newName.equals(lastName)) {
          continueUpdate=true;
        }
      }
      if (!continueUpdate) {
        return;
      }
    }
    avatarDrawable.setInfo(currentUser);
    lastStatus=currentUser.status != null ? currentUser.status.expires : 0;
    if (currentName != null) {
      lastName=null;
      nameTextView.setText(currentName,true);
    }
 else {
      lastName=newName == null ? UserObject.getUserName(currentUser) : newName;
      nameTextView.setText(lastName);
    }
    if (currentStatus == null) {
      if (currentUser.bot) {
        statusTextView.setTag(Theme.key_windowBackgroundWhiteGrayText);
        statusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        statusTextView.setText(LocaleController.getString("Bot",R.string.Bot));
      }
 else {
        if (currentUser.id == UserConfig.getInstance(currentAccount).getClientUserId() || currentUser.status != null && currentUser.status.expires > ConnectionsManager.getInstance(currentAccount).getCurrentTime() || MessagesController.getInstance(currentAccount).onlinePrivacy.containsKey(currentUser.id)) {
          statusTextView.setTag(Theme.key_windowBackgroundWhiteBlueText);
          statusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
          statusTextView.setText(LocaleController.getString("Online",R.string.Online));
        }
 else {
          statusTextView.setTag(Theme.key_windowBackgroundWhiteGrayText);
          statusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
          statusTextView.setText(LocaleController.formatUserStatus(currentAccount,currentUser));
        }
      }
    }
    avatarImageView.setImage(ImageLocation.getForUser(currentUser,false),"50_50",avatarDrawable,currentUser);
  }
 else {
    TLRPC.Chat currentChat=(TLRPC.Chat)currentObject;
    if (currentChat.photo != null) {
      photo=currentChat.photo.photo_small;
    }
    if (mask != 0) {
      boolean continueUpdate=false;
      if ((mask & MessagesController.UPDATE_MASK_AVATAR) != 0) {
        if (lastAvatar != null && photo == null || lastAvatar == null && photo != null || lastAvatar != null && photo != null && (lastAvatar.volume_id != photo.volume_id || lastAvatar.local_id != photo.local_id)) {
          continueUpdate=true;
        }
      }
      if (!continueUpdate && currentName == null && lastName != null && (mask & MessagesController.UPDATE_MASK_NAME) != 0) {
        newName=currentChat.title;
        if (!newName.equals(lastName)) {
          continueUpdate=true;
        }
      }
      if (!continueUpdate) {
        return;
      }
    }
    avatarDrawable.setInfo(currentChat);
    if (currentName != null) {
      lastName=null;
      nameTextView.setText(currentName,true);
    }
 else {
      lastName=newName == null ? currentChat.title : newName;
      nameTextView.setText(lastName);
    }
    if (currentStatus == null) {
      statusTextView.setTag(Theme.key_windowBackgroundWhiteGrayText);
      statusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
      if (currentChat.participants_count != 0) {
        statusTextView.setText(LocaleController.formatPluralString("Members",currentChat.participants_count));
      }
 else       if (TextUtils.isEmpty(currentChat.username)) {
        statusTextView.setText(LocaleController.getString("MegaPrivate",R.string.MegaPrivate));
      }
 else {
        statusTextView.setText(LocaleController.getString("MegaPublic",R.string.MegaPublic));
      }
    }
    avatarImageView.setImage(ImageLocation.getForChat(currentChat,false),"50_50",avatarDrawable,currentChat);
  }
  if (currentStatus != null) {
    statusTextView.setText(currentStatus,true);
    statusTextView.setTag(Theme.key_windowBackgroundWhiteGrayText);
    statusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
  }
}
