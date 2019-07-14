private void clearHistory(boolean overwrite){
  messages.clear();
  waitingForLoad.clear();
  messagesByDays.clear();
  groupedMessagesMap.clear();
  for (int a=1; a >= 0; a--) {
    messagesDict[a].clear();
    if (currentEncryptedChat == null) {
      maxMessageId[a]=Integer.MAX_VALUE;
      minMessageId[a]=Integer.MIN_VALUE;
    }
 else {
      maxMessageId[a]=Integer.MIN_VALUE;
      minMessageId[a]=Integer.MAX_VALUE;
    }
    maxDate[a]=Integer.MIN_VALUE;
    minDate[a]=0;
    selectedMessagesIds[a].clear();
    selectedMessagesCanCopyIds[a].clear();
    selectedMessagesCanStarIds[a].clear();
  }
  hideActionMode();
  updatePinnedMessageView(true);
  if (botButtons != null) {
    botButtons=null;
    if (chatActivityEnterView != null) {
      chatActivityEnterView.setButtons(null,false);
    }
  }
  if (overwrite) {
    if (chatAdapter != null) {
      progressView.setVisibility(chatAdapter.botInfoRow == -1 ? View.VISIBLE : View.INVISIBLE);
      chatListView.setEmptyView(null);
    }
    for (int a=0; a < 2; a++) {
      endReached[a]=false;
      cacheEndReached[a]=false;
      forwardEndReached[a]=true;
    }
    first=true;
    firstLoading=true;
    loading=true;
    startLoadFromMessageId=0;
    needSelectFromMessageId=false;
    waitingForLoad.add(lastLoadIndex);
    MessagesController.getInstance(currentAccount).loadMessages(dialog_id,AndroidUtilities.isTablet() ? 30 : 20,0,0,true,0,classGuid,2,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
  }
 else {
    if (progressView != null) {
      progressView.setVisibility(View.INVISIBLE);
      chatListView.setEmptyView(emptyViewContainer);
    }
  }
  if (chatAdapter != null) {
    chatAdapter.notifyDataSetChanged();
  }
  if (currentEncryptedChat == null && currentUser != null && currentUser.bot && botUser == null) {
    botUser="";
    updateBottomOverlay();
  }
}
