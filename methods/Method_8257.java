public void scrollToMessageId(int id,int fromMessageId,boolean select,int loadIndex,boolean smooth){
  wasManualScroll=true;
  MessageObject object=messagesDict[loadIndex].get(id);
  boolean query=false;
  if (object != null) {
    int index=messages.indexOf(object);
    if (index != -1) {
      removeSelectedMessageHighlight();
      if (select) {
        highlightMessageId=id;
      }
      int yOffset=getScrollOffsetForMessage(object);
      if (smooth) {
        if (messages.get(messages.size() - 1) == object) {
          chatListView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
        }
 else {
          chatListView.smoothScrollToPosition(chatAdapter.messagesStartRow + messages.indexOf(object));
        }
      }
 else {
        if (messages.get(messages.size() - 1) == object) {
          chatLayoutManager.scrollToPositionWithOffset(chatAdapter.getItemCount() - 1,yOffset,false);
        }
 else {
          chatLayoutManager.scrollToPositionWithOffset(chatAdapter.messagesStartRow + messages.indexOf(object),yOffset,false);
        }
      }
      updateVisibleRows();
      boolean found=false;
      int count=chatListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=chatListView.getChildAt(a);
        if (view instanceof ChatMessageCell) {
          ChatMessageCell cell=(ChatMessageCell)view;
          MessageObject messageObject=cell.getMessageObject();
          if (messageObject != null && messageObject.getId() == object.getId()) {
            found=true;
            view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
            break;
          }
        }
 else         if (view instanceof ChatActionCell) {
          ChatActionCell cell=(ChatActionCell)view;
          MessageObject messageObject=cell.getMessageObject();
          if (messageObject != null && messageObject.getId() == object.getId()) {
            found=true;
            view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
            break;
          }
        }
      }
      if (!found) {
        showPagedownButton(true,true);
      }
    }
 else {
      query=true;
    }
  }
 else {
    query=true;
  }
  if (query) {
    if (currentEncryptedChat != null && !MessagesStorage.getInstance(currentAccount).checkMessageId(dialog_id,startLoadFromMessageId)) {
      return;
    }
    waitingForLoad.clear();
    waitingForReplyMessageLoad=true;
    removeSelectedMessageHighlight();
    scrollToMessagePosition=-10000;
    startLoadFromMessageId=id;
    if (id == createUnreadMessageAfterId) {
      createUnreadMessageAfterIdLoading=true;
    }
    waitingForLoad.add(lastLoadIndex);
    MessagesController.getInstance(currentAccount).loadMessages(loadIndex == 0 ? dialog_id : mergeDialogId,AndroidUtilities.isTablet() ? 30 : 20,startLoadFromMessageId,0,true,0,classGuid,3,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
  }
 else {
    showFloatingDateView(false);
  }
  returnToMessageId=fromMessageId;
  returnToLoadIndex=loadIndex;
  needSelectFromMessageId=select;
}
