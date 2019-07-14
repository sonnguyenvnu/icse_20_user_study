private void updateRows(){
  currentChat=MessagesController.getInstance(currentAccount).getChat(chatId);
  if (currentChat == null) {
    return;
  }
  recentActionsRow=-1;
  addNewRow=-1;
  addNew2Row=-1;
  addNewSectionRow=-1;
  restricted1SectionRow=-1;
  participantsStartRow=-1;
  participantsDividerRow=-1;
  participantsDivider2Row=-1;
  participantsEndRow=-1;
  participantsInfoRow=-1;
  blockedEmptyRow=-1;
  permissionsSectionRow=-1;
  sendMessagesRow=-1;
  sendMediaRow=-1;
  sendStickersRow=-1;
  sendPollsRow=-1;
  embedLinksRow=-1;
  addUsersRow=-1;
  pinMessagesRow=-1;
  changeInfoRow=-1;
  removedUsersRow=-1;
  contactsHeaderRow=-1;
  contactsStartRow=-1;
  contactsEndRow=-1;
  botHeaderRow=-1;
  botStartRow=-1;
  botEndRow=-1;
  membersHeaderRow=-1;
  rowCount=0;
  if (type == TYPE_KICKED) {
    permissionsSectionRow=rowCount++;
    sendMessagesRow=rowCount++;
    sendMediaRow=rowCount++;
    sendStickersRow=rowCount++;
    sendPollsRow=rowCount++;
    embedLinksRow=rowCount++;
    addUsersRow=rowCount++;
    pinMessagesRow=rowCount++;
    changeInfoRow=rowCount++;
    if (ChatObject.isChannel(currentChat)) {
      participantsDivider2Row=rowCount++;
      removedUsersRow=rowCount++;
    }
    participantsDividerRow=rowCount++;
    if (ChatObject.canBlockUsers(currentChat)) {
      addNewRow=rowCount++;
    }
    if (!participants.isEmpty()) {
      participantsStartRow=rowCount;
      rowCount+=participants.size();
      participantsEndRow=rowCount;
    }
    if (addNewRow != -1 || participantsStartRow != -1) {
      addNewSectionRow=rowCount++;
    }
  }
 else   if (type == TYPE_BANNED) {
    if (ChatObject.canBlockUsers(currentChat)) {
      addNewRow=rowCount++;
      if (!participants.isEmpty()) {
        participantsInfoRow=rowCount++;
      }
    }
    if (!participants.isEmpty()) {
      restricted1SectionRow=rowCount++;
      participantsStartRow=rowCount;
      rowCount+=participants.size();
      participantsEndRow=rowCount;
    }
    if (participantsStartRow != -1) {
      if (participantsInfoRow == -1) {
        participantsInfoRow=rowCount++;
      }
 else {
        addNewSectionRow=rowCount++;
      }
    }
 else {
      blockedEmptyRow=rowCount++;
    }
  }
 else   if (type == TYPE_ADMIN) {
    if (ChatObject.isChannel(currentChat) && currentChat.megagroup && (info == null || info.participants_count <= 200)) {
      recentActionsRow=rowCount++;
      addNewSectionRow=rowCount++;
    }
    if (ChatObject.canAddAdmins(currentChat)) {
      addNewRow=rowCount++;
    }
    if (!participants.isEmpty()) {
      participantsStartRow=rowCount;
      rowCount+=participants.size();
      participantsEndRow=rowCount;
    }
    participantsInfoRow=rowCount++;
  }
 else   if (type == TYPE_USERS) {
    if (selectType == 0 && ChatObject.canAddUsers(currentChat)) {
      addNewRow=rowCount++;
    }
    boolean hasAnyOther=false;
    if (!contacts.isEmpty()) {
      contactsHeaderRow=rowCount++;
      contactsStartRow=rowCount;
      rowCount+=contacts.size();
      contactsEndRow=rowCount;
      hasAnyOther=true;
    }
    if (!bots.isEmpty()) {
      botHeaderRow=rowCount++;
      botStartRow=rowCount;
      rowCount+=bots.size();
      botEndRow=rowCount;
      hasAnyOther=true;
    }
    if (!participants.isEmpty()) {
      if (hasAnyOther) {
        membersHeaderRow=rowCount++;
      }
      participantsStartRow=rowCount;
      rowCount+=participants.size();
      participantsEndRow=rowCount;
    }
    if (rowCount != 0) {
      participantsInfoRow=rowCount++;
    }
  }
}
