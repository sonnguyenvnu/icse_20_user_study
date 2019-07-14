private void clearChatData(){
  messages.clear();
  messagesByDays.clear();
  waitingForLoad.clear();
  groupedMessagesMap.clear();
  progressView.setVisibility(chatAdapter.botInfoRow == -1 ? View.VISIBLE : View.INVISIBLE);
  chatListView.setEmptyView(null);
  for (int a=0; a < 2; a++) {
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
    endReached[a]=false;
    cacheEndReached[a]=false;
    forwardEndReached[a]=true;
  }
  first=true;
  firstLoading=true;
  loading=true;
  loadingForward=false;
  waitingForReplyMessageLoad=false;
  startLoadFromMessageId=0;
  last_message_id=0;
  unreadMessageObject=null;
  createUnreadMessageAfterId=0;
  createUnreadMessageAfterIdLoading=false;
  needSelectFromMessageId=false;
  chatAdapter.notifyDataSetChanged();
}
