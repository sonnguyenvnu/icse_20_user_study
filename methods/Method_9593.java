private void updateRowsIds(){
  rowCount=0;
  emptyRow=-1;
  infoHeaderRow=-1;
  phoneRow=-1;
  userInfoRow=-1;
  channelInfoRow=-1;
  usernameRow=-1;
  settingsTimerRow=-1;
  settingsKeyRow=-1;
  notificationsDividerRow=-1;
  notificationsRow=-1;
  infoSectionRow=-1;
  settingsSectionRow=-1;
  membersHeaderRow=-1;
  membersStartRow=-1;
  membersEndRow=-1;
  addMemberRow=-1;
  subscribersRow=-1;
  administratorsRow=-1;
  blockedUsersRow=-1;
  membersSectionRow=-1;
  sharedHeaderRow=-1;
  photosRow=-1;
  filesRow=-1;
  linksRow=-1;
  audioRow=-1;
  voiceRow=-1;
  groupsInCommonRow=-1;
  sharedSectionRow=-1;
  unblockRow=-1;
  startSecretChatRow=-1;
  leaveChannelRow=-1;
  joinRow=-1;
  lastSectionRow=-1;
  boolean hasMedia=false;
  for (int a=0; a < lastMediaCount.length; a++) {
    if (lastMediaCount[a] > 0) {
      hasMedia=true;
      break;
    }
  }
  if (user_id != 0 && LocaleController.isRTL) {
    emptyRow=rowCount++;
  }
  if (user_id != 0) {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
    boolean hasInfo=userInfo != null && !TextUtils.isEmpty(userInfo.about) || user != null && !TextUtils.isEmpty(user.username);
    boolean hasPhone=user != null && !TextUtils.isEmpty(user.phone);
    infoHeaderRow=rowCount++;
    if (!isBot && (hasPhone || !hasPhone && !hasInfo)) {
      phoneRow=rowCount++;
    }
    if (userInfo != null && !TextUtils.isEmpty(userInfo.about)) {
      userInfoRow=rowCount++;
    }
    if (user != null && !TextUtils.isEmpty(user.username)) {
      usernameRow=rowCount++;
    }
    if (phoneRow != -1 || userInfoRow != -1 || usernameRow != -1) {
      notificationsDividerRow=rowCount++;
    }
    if (user_id != UserConfig.getInstance(currentAccount).getClientUserId()) {
      notificationsRow=rowCount++;
    }
    infoSectionRow=rowCount++;
    if (currentEncryptedChat instanceof TLRPC.TL_encryptedChat) {
      settingsTimerRow=rowCount++;
      settingsKeyRow=rowCount++;
      settingsSectionRow=rowCount++;
    }
    if (hasMedia || userInfo != null && userInfo.common_chats_count != 0) {
      sharedHeaderRow=rowCount++;
      if (lastMediaCount[DataQuery.MEDIA_PHOTOVIDEO] > 0) {
        photosRow=rowCount++;
      }
 else {
        photosRow=-1;
      }
      if (lastMediaCount[DataQuery.MEDIA_FILE] > 0) {
        filesRow=rowCount++;
      }
 else {
        filesRow=-1;
      }
      if (lastMediaCount[DataQuery.MEDIA_URL] > 0) {
        linksRow=rowCount++;
      }
 else {
        linksRow=-1;
      }
      if (lastMediaCount[DataQuery.MEDIA_MUSIC] > 0) {
        audioRow=rowCount++;
      }
 else {
        audioRow=-1;
      }
      if (lastMediaCount[DataQuery.MEDIA_AUDIO] > 0) {
        voiceRow=rowCount++;
      }
 else {
        voiceRow=-1;
      }
      if (userInfo != null && userInfo.common_chats_count != 0) {
        groupsInCommonRow=rowCount++;
      }
      sharedSectionRow=rowCount++;
    }
    if (user != null && !isBot && currentEncryptedChat == null && user.id != UserConfig.getInstance(currentAccount).getClientUserId()) {
      if (userBlocked) {
        unblockRow=rowCount++;
      }
 else {
        startSecretChatRow=rowCount++;
      }
      lastSectionRow=rowCount++;
    }
  }
 else   if (chat_id != 0) {
    if (chat_id > 0) {
      if (chatInfo != null && !TextUtils.isEmpty(chatInfo.about) || !TextUtils.isEmpty(currentChat.username)) {
        infoHeaderRow=rowCount++;
        if (chatInfo != null && !TextUtils.isEmpty(chatInfo.about)) {
          channelInfoRow=rowCount++;
        }
        if (!TextUtils.isEmpty(currentChat.username)) {
          usernameRow=rowCount++;
        }
      }
      if (channelInfoRow != -1 || usernameRow != -1) {
        notificationsDividerRow=rowCount++;
      }
      notificationsRow=rowCount++;
      infoSectionRow=rowCount++;
      if (ChatObject.isChannel(currentChat) && !currentChat.megagroup) {
        if (chatInfo != null && (currentChat.creator || chatInfo.can_view_participants)) {
          membersHeaderRow=rowCount++;
          subscribersRow=rowCount++;
          administratorsRow=rowCount++;
          if (chatInfo.banned_count != 0 || chatInfo.kicked_count != 0) {
            blockedUsersRow=rowCount++;
          }
          membersSectionRow=rowCount++;
        }
      }
      if (hasMedia) {
        sharedHeaderRow=rowCount++;
        if (lastMediaCount[DataQuery.MEDIA_PHOTOVIDEO] > 0) {
          photosRow=rowCount++;
        }
 else {
          photosRow=-1;
        }
        if (lastMediaCount[DataQuery.MEDIA_FILE] > 0) {
          filesRow=rowCount++;
        }
 else {
          filesRow=-1;
        }
        if (lastMediaCount[DataQuery.MEDIA_URL] > 0) {
          linksRow=rowCount++;
        }
 else {
          linksRow=-1;
        }
        if (lastMediaCount[DataQuery.MEDIA_MUSIC] > 0) {
          audioRow=rowCount++;
        }
 else {
          audioRow=-1;
        }
        if (lastMediaCount[DataQuery.MEDIA_AUDIO] > 0) {
          voiceRow=rowCount++;
        }
 else {
          voiceRow=-1;
        }
        sharedSectionRow=rowCount++;
      }
      if (ChatObject.isChannel(currentChat)) {
        if (!currentChat.creator && !currentChat.left && !currentChat.kicked && !currentChat.megagroup) {
          leaveChannelRow=rowCount++;
          lastSectionRow=rowCount++;
        }
        if (chatInfo != null && currentChat.megagroup && chatInfo.participants != null && !chatInfo.participants.participants.isEmpty()) {
          if (!ChatObject.isNotInChat(currentChat) && currentChat.megagroup && ChatObject.canAddUsers(currentChat) && (chatInfo == null || chatInfo.participants_count < MessagesController.getInstance(currentAccount).maxMegagroupCount)) {
            addMemberRow=rowCount++;
          }
 else {
            membersHeaderRow=rowCount++;
          }
          membersStartRow=rowCount;
          rowCount+=chatInfo.participants.participants.size();
          membersEndRow=rowCount;
          membersSectionRow=rowCount++;
        }
        if (lastSectionRow == -1 && currentChat.left && !currentChat.kicked) {
          joinRow=rowCount++;
          lastSectionRow=rowCount++;
        }
      }
 else       if (chatInfo != null) {
        if (!(chatInfo.participants instanceof TLRPC.TL_chatParticipantsForbidden)) {
          if (ChatObject.canAddUsers(currentChat) || currentChat.default_banned_rights == null || !currentChat.default_banned_rights.invite_users) {
            addMemberRow=rowCount++;
          }
 else {
            membersHeaderRow=rowCount++;
          }
          membersStartRow=rowCount;
          rowCount+=chatInfo.participants.participants.size();
          membersEndRow=rowCount;
          membersSectionRow=rowCount++;
        }
      }
    }
 else     if (!ChatObject.isChannel(currentChat) && chatInfo != null && !(chatInfo.participants instanceof TLRPC.TL_chatParticipantsForbidden)) {
      membersHeaderRow=rowCount++;
      membersStartRow=rowCount;
      rowCount+=chatInfo.participants.participants.size();
      membersEndRow=rowCount;
      membersSectionRow=rowCount++;
      addMemberRow=rowCount++;
    }
  }
}
