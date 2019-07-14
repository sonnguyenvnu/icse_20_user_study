private void updateVisibleRows(){
  if (chatListView == null) {
    return;
  }
  int lastVisibleItem=RecyclerView.NO_POSITION;
  if (!wasManualScroll && unreadMessageObject != null && chatListView.getMeasuredHeight() != 0) {
    int pos=messages.indexOf(unreadMessageObject);
    if (pos >= 0) {
      lastVisibleItem=chatAdapter.messagesStartRow + pos;
    }
  }
  int count=chatListView.getChildCount();
  MessageObject editingMessageObject=chatActivityEnterView != null ? chatActivityEnterView.getEditingMessageObject() : null;
  for (int a=0; a < count; a++) {
    View view=chatListView.getChildAt(a);
    if (view instanceof ChatMessageCell) {
      ChatMessageCell cell=(ChatMessageCell)view;
      MessageObject messageObject=cell.getMessageObject();
      boolean disableSelection=false;
      boolean selected=false;
      if (actionBar.isActionModeShowed()) {
        cell.setCheckBoxVisible(true,true);
        int idx=messageObject.getDialogId() == dialog_id ? 0 : 1;
        if (messageObject == editingMessageObject || selectedMessagesIds[idx].indexOfKey(messageObject.getId()) >= 0) {
          setCellSelectionBackground(messageObject,cell,idx,true);
          selected=true;
        }
 else {
          cell.setDrawSelectionBackground(false);
          cell.setChecked(false,false,true);
        }
        disableSelection=true;
      }
 else {
        cell.setDrawSelectionBackground(false);
        cell.setCheckBoxVisible(false,true);
        cell.setChecked(false,false,true);
      }
      cell.setMessageObject(cell.getMessageObject(),cell.getCurrentMessagesGroup(),cell.isPinnedBottom(),cell.isPinnedTop());
      if (cell != scrimView) {
        cell.setCheckPressed(!disableSelection,disableSelection && selected);
      }
      cell.setHighlighted(highlightMessageId != Integer.MAX_VALUE && messageObject != null && messageObject.getId() == highlightMessageId);
      if (highlightMessageId != Integer.MAX_VALUE) {
        startMessageUnselect();
      }
      if (searchContainer != null && searchContainer.getVisibility() == View.VISIBLE && DataQuery.getInstance(currentAccount).isMessageFound(messageObject.getId(),messageObject.getDialogId() == mergeDialogId) && DataQuery.getInstance(currentAccount).getLastSearchQuery() != null) {
        cell.setHighlightedText(DataQuery.getInstance(currentAccount).getLastSearchQuery());
      }
 else {
        cell.setHighlightedText(null);
      }
    }
 else     if (view instanceof ChatActionCell) {
      ChatActionCell cell=(ChatActionCell)view;
      cell.setMessageObject(cell.getMessageObject());
    }
  }
  chatListView.invalidate();
  if (lastVisibleItem != RecyclerView.NO_POSITION) {
    int top=chatListView.getMeasuredHeight() - chatListView.getPaddingBottom() - chatListView.getPaddingTop() - AndroidUtilities.dp(29);
    chatLayoutManager.scrollToPositionWithOffset(lastVisibleItem,top);
  }
}
