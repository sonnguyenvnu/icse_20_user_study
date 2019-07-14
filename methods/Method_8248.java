private void scrollToLastMessage(boolean pagedown){
  if (forwardEndReached[0] && first_unread_id == 0 && startLoadFromMessageId == 0) {
    if (pagedown && chatLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
      showPagedownButton(false,true);
      removeSelectedMessageHighlight();
      updateVisibleRows();
    }
 else {
      chatLayoutManager.scrollToPositionWithOffset(0,0);
    }
  }
 else {
    clearChatData();
    waitingForLoad.add(lastLoadIndex);
    MessagesController.getInstance(currentAccount).loadMessages(dialog_id,30,0,0,true,0,classGuid,0,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
  }
}
